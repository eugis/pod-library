package tp4.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tp4.Book;
import tp4.LibraryService;
import tp4.server.LibraryImpl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Collection;

public class Client {
    private static Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        logger.info("library Client Starting ...");

        //LibraryService service = (LibraryService) Naming.lookup("//192.168.0.2:1099/service");

        Collection<String[]> books = Arrays.asList(
                new String[] { "3", "978-0345533487", "A Knight of the Seven Kingdoms",
                        "2015-10-06", "Martin", "George R. R." },
                new String[] { "4", "978-0441294671", "God Emperor of Dune", "1987-06-15",
                        "Herbert", "Frank" },
                new String[] { "2", "978-0451210845", "The Gunslinger", "2003-07-01", "King",
                        "Stephen" },
                new String[] { "5", "978-0307292063", "The Foundation Trilogy", "2011-11-25",
                        "Asimov", "Isaac" },
                new String[] { "4", "978-0765351494", "Sandworms of Dune", "2008-07-01",
                        "Herbert", "Brian" },
                new String[] { "1", "978-0307743657", "The Shining", "2012-06-26", "King",
                        "Stephen" },
                new String[] { "2", "978-0553328257", "The Complete Sherlock Holmes",
                        "1986-10-01", "Conan Doyle", "Arthur" });

        LibraryService s = new LibraryImpl(books);
        logger.info("list of books {}", s.listBooks());
        Book b = new Book("978-0345533487", "A Knight of the Seven Kingdoms",
                "2015-10-06", "Martin", "George R. R.");
        //s.returnBook(b);
        logger.info("get book {}", s.lendBook(b.getISBN()));
        logger.info("get book {}", s.lendBook(b.getISBN()));
        logger.info("get book {}", s.lendBook(b.getISBN()));
        //logger.info("get book {}", s.lendBook(b.getISBN()));
        s.returnBook(b);
        logger.info("get book {}", s.lendBook(b.getISBN()));
    }
}
