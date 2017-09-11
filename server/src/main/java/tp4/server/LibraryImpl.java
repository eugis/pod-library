package tp4.server;

import tp4.Book;
import tp4.LibraryService;

import java.rmi.MarshalledObject;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationID;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class LibraryImpl implements LibraryService {

    Map<Book, StockCounter> locationBooks = new HashMap<>();

    public LibraryImpl(Collection<String[]> books, ActivationID id, MarshalledObject data) throws RemoteException {
        Activatable.exportObject(this, id, 0);
        // TODO: rewrite this. Is HORRIBLE!!
        locationBooks = books.stream().collect(toMap(e -> new Book(e[1], e[2], e[3], e[4], e[5]), e2 -> new StockCounter(Integer.parseInt(e2[0]))));
        /*for ( String[] info : books ) {
            //System.out.print(info);
            Book b = new Book(info[1], info[2], info[3], info[4], info[5]);
            locationBooks.put(b, new StockCounter(Integer.parseInt(info[0])));
        }*/
    }

    @Override
    public List<String> listBooks() throws RemoteException {
        return  locationBooks.keySet().stream().map(b -> b.getISBN() + b.getTitle()).collect(Collectors.toList());
    }

    @Override
    public Book lendBook(String isbn) throws RemoteException, RuntimeException {
        Book askingBook = locationBooks.keySet().stream().filter(b -> b.getISBN().equals(isbn)).collect(Collectors.toList()).get(0);
        StockCounter bookInformation = locationBooks.get(askingBook);
        if (bookInformation.reduceActualStock()) {
            return askingBook;
        }
        throw new RuntimeException("There is no book to lend");
    }

    @Override
    public void returnBook(Book bookToReturn) throws RemoteException, RuntimeException {
        StockCounter bookInformation = locationBooks.get(bookToReturn);
        if (!bookInformation.incrementActualStock()) {
            throw new RuntimeException("The book never has been lended");
        }
    }
}
