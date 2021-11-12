package com.sun.controller;

import com.sun.dao.CartDao;
import com.sun.entity.Book;
import com.sun.entity.Cart;
import com.sun.entity.User;
import com.sun.service.BookService;
import com.sun.service.CartService;
import com.sun.service.UserService;
import com.sun.service.impl.BookServiceImpl;
import com.sun.service.impl.CartServiceImpl;
import com.sun.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    private UserService userService;
    private BookService bookService;
    private CartService cartService;
    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request){
        userService = new UserServiceImpl();
        if(userService.login(user,request)) {
            System.out.println("controller：登录成功");
            return "index";
        }else {
            System.out.println("controller:登录失败");
            return "index";
        }

    }
    @RequestMapping("/registered")
    public String registered(User user){
        userService = new UserServiceImpl();
        System.out.println(user.toString());
        if(userService.register(user)){
            System.out.println(user+"注册成功");
        }

        return "index";
    }
    @RequestMapping("/search")
    public String search(@RequestParam(value = "search",defaultValue="")String search,Model model){
        System.out.println("controller:"+search);
        bookService = new BookServiceImpl();
        List<Book> books = bookService.searchBooks(search);
        model.addAttribute("key",search);
        model.addAttribute("books",books);
        return "searcherResout";
    }
    //页面跳转 ↓
    @RequestMapping("toIndex")
    public String toIndex(){
        return "index";
    }
    @RequestMapping("toAbout")
    public String toAbout(Model model){

        return "about";
    }
    @RequestMapping("toBooks")
    public String toBooks(@RequestParam(value = "type",defaultValue="-1")Integer type,Model model){
        bookService = new BookServiceImpl();
        System.out.println("controller:"+type);
        List<Book> books = bookService.getTagBooks(type);
        model.addAttribute("books",books);
        return "books";
    }

    @RequestMapping("toInformation")
    public String toInformation(HttpServletRequest request, Model model){
        userService = new UserServiceImpl();
        User user = userService.getInformation(request);
        model.addAttribute("user",user);
        System.out.println(user);
        return "information";
    }

    @RequestMapping("toInformationModification")
    public String toInformationModification(){
        return "informationModification";
    }

    @RequestMapping("toRecharge")
    public String toRecharge(){
        return "recharge";
    }

    @RequestMapping("toShopingCart")
    public String toShopingCart(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        model.addAttribute("user",user);
        return "shopingCart";
    }

    @RequestMapping("toSingle")
    public String toSingle(@RequestParam(value = "bid",defaultValue="")String bid,Model model) throws IOException {
        bookService = new BookServiceImpl();
        Book book = bookService.getOneBook(Integer.parseInt(bid));
        model.addAttribute("book",book);
        return "single";
    }
    //页面跳转↑

    //信息修改
    @RequestMapping("/updateInformation")
    public String updateInformation(User user,HttpServletRequest request){
        System.out.println("controller"+user.toString());
        userService = new UserServiceImpl();
        userService.updateInfo(user,request);
        return "redirect:/toInformation";
    }
    //充值
    @RequestMapping("/recharge")
    public String recharge(@RequestParam(value = "money",defaultValue="0")String money,HttpServletRequest request){
        System.out.println(money);
        userService = new UserServiceImpl();
        userService.recharge(Float.parseFloat(money),request);
        return "redirect:/toInformation";
    }

    @RequestMapping("removeBook")
    public String removeBook(@RequestParam(value = "bid",defaultValue="")String bid,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        cartService = new CartServiceImpl();
        cartService.removeOne(user,Integer.parseInt(bid));
        Book b = null;
        for (Book book:user.getBooks()
             ) {
            if(book.getBid()==Integer.parseInt(bid)){
                b = book;
            }
        }
        user.getBooks().remove(b);
        System.out.println(bid);
        return "shopingCart";
    }
    @RequestMapping("/buyAll")
    public String buyAll(Model model,HttpServletRequest request)
    {
        HttpSession session = request.getSession();

        model.addAttribute("user",((User)session.getAttribute("user")));
        model.addAttribute("isCart",true);
        return "payFor";
    }
    @RequestMapping("/payForCart")
    public String payForCart(@RequestParam(value = "sum",defaultValue="")Float sum,Model model,HttpServletRequest request){
        userService = new UserServiceImpl();
        userService.payFor(sum,request);
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        user.setBooks(new ArrayList<Book>());

        cartService = new CartServiceImpl();
        cartService.removeAll(user);
        model.addAttribute("message","购买成功");
        return "message";
    }
    @RequestMapping("/buySingle")
    public String buySingle(@RequestParam(value = "bid",defaultValue = "")String bid,Model model,HttpServletRequest request) throws IOException {
        bookService = new BookServiceImpl();
        System.out.println(bid);
        //下面的本应放在业务逻辑层
        HttpSession session = request.getSession();
        User user = new User ((User)session.getAttribute("user"));
        List<Book> books = new ArrayList<>();
        Book book = bookService.getOneBook(Integer.parseInt(bid));
        book.setNums(1);
        books.add(book);
        user.setBooks(books);
        model.addAttribute("user",user);
        model.addAttribute("isCart",false);
        return "payFor";
    }
    @RequestMapping("/payForSingle")
    public String payForSingle(@RequestParam(value = "sum",defaultValue="")Float sum,Model model,HttpServletRequest request){
        userService = new UserServiceImpl();
        userService.payFor(sum,request);
        model.addAttribute("message","购买成功");
        return "message";
    }
    @RequestMapping("addToCart")
    public String addToCart(@RequestParam(value = "bid",defaultValue="")String bid,HttpServletRequest request,Model model){
        System.out.println("controller:bid"+bid);
        cartService = new CartServiceImpl();
        System.out.println("controller:addtocart3");
        cartService.addToCart(Integer.parseInt(bid),request);
        System.out.println("controller:addtocart3");
        userService = new UserServiceImpl();
        System.out.println("controller:addtocart3");
        userService.login((User) request.getSession().getAttribute("user"),request);
        return "redirect:/toShopingCart";
    }
}
