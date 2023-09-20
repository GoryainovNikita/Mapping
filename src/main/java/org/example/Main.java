package org.example;

import dao.*;
import entities.*;
import helper.Feature;
import helper.Rating;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    private static SessionFactory sessionFactory = getSessionFactory();

    public static SessionFactory getSessionFactory(){
        return new Configuration()
                .configure()
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.film();
    }

    private void createCustomer(){
        try(Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();

            CityDao cityDao = new CityDao(sessionFactory);
            City city = cityDao.getByName("Moscow");

            Address address = new Address();
            address.setAddress("Volokolamskaya str");
            address.setDistrict("aaaa");
            address.setPhone("88005553535");
            address.setPostalCode("8800");
            address.setCity(city);

            AddressDao addressDao = new AddressDao(sessionFactory);
            addressDao.create(address);

            StoreDao storeDao = new StoreDao(sessionFactory);
            Store store = storeDao.getItems(0,1).get(0);

            Customer customer = new Customer();
            customer.setStore(store);
            customer.setFirstName("Ivan");
            customer.setLastName("Ivanov");
            customer.setEmail("Ivanov.com");
            customer.setAddress(address);
            customer.setActive(true);

            CustomerDao customerDao = new CustomerDao(sessionFactory);
            customerDao.create(customer);

            session.getTransaction().commit();
        }
    }

    private void returnFilm(){
        try(Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();

            CustomerDao customerDao = new CustomerDao(sessionFactory);
            Customer customer = customerDao.getItems(334, 335).get(0);
            Set<Rental> rentalList = customer.getRentals();
            for (var elem : rentalList){
                if(elem.getId() == 11541){
                    elem.setReturnDate(LocalDateTime.now());
                    break;
                }
            }


            session.getTransaction().commit();
        }
    }

    private void rentFilm(){
        try(Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();

            CustomerDao customerDao = new CustomerDao(sessionFactory);
            Customer customer = customerDao.getItems(599,600).get(0);

            Store store = customer.getStore();
            Staff staff = store.getStaff();

            FilmDao filmDao = new FilmDao(sessionFactory);
            List<Film> list = filmDao.getAllFilmsCurrentlyNotRentedInStore(store.getId());

            Film film = list.get((int) Math.random() * list.size());

            InventoryDao inventoryDao = new InventoryDao(sessionFactory);

            Rental rental = new Rental();

            rental.setRentalDate(LocalDateTime.now());
            rental.setCustomer(customer);
            rental.setStaff(staff);
            rental.setInventory(inventoryDao.findInventoryByFilm(film).get(0));

            RentalDao rentalDao = new RentalDao(sessionFactory);
            rentalDao.create(rental);

            Payment payment = new Payment();
            payment.setStaff(staff);
            payment.setCustomer(customer);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setAmount(BigDecimal.TWO);
            payment.setRental(rental);

            PaymentDao paymentDao = new PaymentDao(sessionFactory);
            paymentDao.create(payment);


            session.getTransaction().commit();
        }
    }

    public void createNewFilm(){
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();


            ActorDao actorDao = new ActorDao(sessionFactory);
            List<Actor> actors = actorDao.getItems(0,5);
            Set<Actor> actorSet = actors.stream().collect(Collectors.toSet());

            LanguageDao languageDao = new LanguageDao(sessionFactory);
            Language language = languageDao.getItems(0,1).get(0);

            CategoryDao categoryDao = new CategoryDao(sessionFactory);
            List<Category> categories = categoryDao.getItems(0,2);
            Set<Category> categorySet = categories.stream().collect(Collectors.toSet());



            Film film = new Film();
            film.setActors(actorSet);
            film.setDescription("Вообще крутяк, там драки и погони");
            film.setLanguage(language);
            film.setLength((short) 190);
            film.setRating(Rating.G);
            film.setReleaseYear((short) 2022);
            film.setRentalDuration((byte) 6);
            film.setRentalRate(BigDecimal.TEN);
            film.setReplacementCost(BigDecimal.TWO);

            Set<Feature> features = new HashSet<>();
            features.add(Feature.Trailers);
            features.add(Feature.DeletedScenes);
            film.setSpecialFeatures(features);
            film.setTitle("Ковбои на конях");
            film.setCategorySet(categorySet);

            FilmDao filmDao = new FilmDao(sessionFactory);
            filmDao.create(film);

            StoreDao storeDao = new StoreDao(sessionFactory);
            List<Store> stores = storeDao.getItems(0,2);

            InventoryDao inventoryDao = new InventoryDao(sessionFactory);
            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(stores.get(0));

            Inventory inventory1 = new Inventory();
            inventory1.setFilm(film);
            inventory1.setStore(stores.get(1));

            inventoryDao.create(inventory);
            inventoryDao.create(inventory1);


            session.getTransaction().commit();

        }

    }
    public void film(){
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            FilmDao filmDao = new FilmDao(sessionFactory);
            Film film = filmDao.getItems(1,1).get(0);
            System.out.println(film.getSpecialFeatures());
            session.getTransaction().commit();

        }
    }

}