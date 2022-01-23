package ru.Netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    Product zero = new Product(0, "zero", 200);
    Repository repo=new Repository();
    ProductManager manager = new ProductManager(repo);
    Book first = new Book(1, "first", 150, "author");
    Book second = new Book(2, "second", 120, "author");
    Book third = new Book(3, "third", 230, "author");
    Book fourth = new Book(4, "fourth", 175, "author");

    Smartphone number1 = new Smartphone(10, "number1", 19000, "maker");
    Smartphone number2 = new Smartphone(20, "number2", 5000, "maker");
    Smartphone number3 = new Smartphone(30, "number3", 3900, "maker");
    Smartphone number4 = new Smartphone(40, "number4", 4900, "maker");

    @Test
    void searchByBook() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(number1);
        manager.add(number2);
        manager.add(number3);
        manager.add(number4);

        manager.searchBy(first.getAuthor());
        manager.searchBy(first.getName());

        Product[] actual = manager.searchBy(first.getName());
        Product[] expected = new Product[]{first};
        assertArrayEquals(expected, actual);
    }
    @Test
    void searchBySmartphone() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(number1);
        manager.add(number2);
        manager.add(number3);
        manager.add(number4);

        manager.searchBy(number1.getMaker());
        manager.searchBy(number1.getName());
        Product[] actual = manager.searchBy(number1.getName());
        Product[] expected = new Product[]{number1};
        assertArrayEquals(expected, actual);

    }
    @Test
    void DeleteByIdBook() {
        int idToDelete=1;
        manager.add(first);
        manager.add(second);
        repo.deleteById(idToDelete);
        manager.searchBy(first.getName());
        Product[] actual = manager.searchBy(first.getName());
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }
    @Test
    void FindByIdBook() {
        int id=1;
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        repo.findById(id);
        manager.searchBy(fourth.getName());
        Product[] actual = manager.searchBy(first.getName());
        Product[] expected = new Product[]{first};
        assertArrayEquals(expected, actual);
    }
    @Test
    void FindByExiIdBook() {
        int id=0;
        manager.add(first);
        manager.add(second);
        repo.findById(id);
        manager.searchBy(zero.getName());
        Product[] actual = manager.searchBy(zero.getName());
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }
    @Test
    void DeleteByExistantIDBook() {
        int idToDelete=132;
        Assertions.assertThrows(NotFoundException.class, ()->{repo.deleteById(idToDelete);});

    }
    @Test
    void DeleteByIdSmartphone() {
        int idToDelete=30;
        manager.add(number3);
        manager.add(number4);
        repo.deleteById(idToDelete);
        manager.searchBy(number3.getName());
        Product[] actual = manager.searchBy(number3.getName());
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);

    }
    @Test
    public void shouldNotSearchByNameProduct() {

        manager.add(zero);
        manager.searchBy(zero.getName());

        Product[] actual = manager.searchBy(zero.getName());
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }


}