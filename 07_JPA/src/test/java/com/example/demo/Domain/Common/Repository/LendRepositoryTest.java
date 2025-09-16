package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Book;
import com.example.demo.Domain.Common.Entity.Lend;
import com.example.demo.Domain.Common.Entity.User;
//import jakarta.transaction.Transactional;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LendRepositoryTest {

    @Autowired
    private LendRepository lendRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void t1() {
        // SELECT ALL
//        List<Lend> list = lendRepository.findAll();
//        list.forEach(System.out::println);

        // INSERT
//        User user = userRepository.findById("user2").get();
//        Book book = bookRepository.findById(3L).get();
//
//        Lend lend = Lend.builder()
//                .id(null)
//                .user(user)
//                .book(book)
//                .build();
//        lendRepository.save(lend);
    }
    @Test
    public void t2(){
        //user1 bookCode 1L 대여
        User user = userRepository.findById("user1").get();
        Book book = bookRepository.findById(1L).get();
        Lend lend1 = new Lend();
        lend1.setBook(book);
        lend1.setUser(user);
        lendRepository.save(lend1);
        //user1 bookCode 2L 대여
        Book book2 = bookRepository.findById(2L).get();
        Lend lend2 = new Lend();
        lend2.setBook(book);
        lend2.setUser(user);
        lendRepository.save(lend2);
        //user2 bookCode 3L 대여
        User user2 = userRepository.findById("user2").get();
        Book book3 = bookRepository.findById(3L).get();
        Lend lend3 = Lend.builder()
//                .id(null)
                .user(user2)
                .book(book3)
                .build();
        lendRepository.save(lend3);
        //user3 bookCode 4L 대여
        User user3 = userRepository.findById("user3").get();
        Book book4 = bookRepository.findById(4L).get();
        Lend lend4 = Lend.builder()
                .user(user3)
                .book(book4)
                .build();
        lendRepository.save(lend4);


    }
    @Test
    public void t3(){
//        List<Lend> list = lendRepository.findAllLendsByUser("user1");
//        list.forEach(System.out::println);

        List<Lend> list = lendRepository.findAllLendsByBook("TEST_BOOK");
        list.forEach(System.out::println);
    }

    @Test
    @Transactional(rollbackFor=Exception.class)
    public void t4(){
        System.out.println("------------------------ FETCH TEST START ----------------------");
        Optional<Lend> lendOptional = lendRepository.findById(1L);
        System.out.println("------------------------ lendRepository.findById(1L) invoke! ----------------------");
        Lend lend = lendOptional.get();
        System.out.println("------------------------ lendOptional.get() invoke! ----------------------");
        User user = lend.getUser();     //!
        System.out.println(user);
        System.out.println("------------------------ lend.getUser() invoke! ----------------------");
        Book book = lend.getBook();     //!
        System.out.println(book);
        System.out.println("------------------------ lend.getBook() invoke! ----------------------");
        System.out.println("------------------------ FETCH TEST END ----------------------");
    }


}