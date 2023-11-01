import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private boolean available;

    public Book(String title) {
        this.title = title;
        this.available = true;
    }


}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(String title) {
        books.add(new Book(title));
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book.getTitle());
            }
        }
    }

    public Book borrowBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                book.borrow();
                return book;
            }
        }
        return null;
    }

    public Book returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.returnBook();
                return book;
            }
        }
        return null;
    }

    public List<Book> getBooks() {
        return books;
    }
}

public class hello {
    private Library library;
    private JTextField titleField;
    private JTextArea outputArea;

    public hello() {
        library = new Library();
        
        // Create the main frame
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create components
        titleField = new JTextField(20);
        JButton addBookButton = new JButton("Add Book");
        JButton displayBooksButton = new JButton("Display Available Books");
        JButton borrowBookButton = new JButton("Borrow Book");
        JButton returnBookButton = new JButton("Return Book");
        outputArea = new JTextArea(10, 40);

        // Add components to the frame
        JPanel inputPanel = new JPanel();
        inputPanel.add(titleField);
        inputPanel.add(addBookButton);
        inputPanel.add(displayBooksButton);
        inputPanel.add(borrowBookButton);
        inputPanel.add(returnBookButton);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Add action listeners
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                library.addBook(title);
                outputArea.append("Book added successfully: " + title + "\n");
                titleField.setText("");
            }
        });

        displayBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputArea.append("Available Books:\n");
                for (Book book : library.getBooks()) {
                    if (book.isAvailable()) {
                        outputArea.append(book.getTitle() + "\n");
                    }
                }
            }
        });

        borrowBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                Book borrowedBook = library.borrowBook(title);
                if (borrowedBook != null) {
                    outputArea.append("You have successfully borrowed: " + borrowedBook.getTitle() + "\n");
                } else {
                    outputArea.append("Book not available or not found.\n");
                }
                titleField.setText("");
            }
        });

        returnBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                Book returnedBook = library.returnBook(title);
                if (returnedBook != null) {
                    outputArea.append("You have successfully returned: " + returnedBook.getTitle() + "\n");
                } else {
                    outputArea.append("Book not found.\n");
                }
                titleField.setText("");
            }
        });

        // Set up the frame
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new hello());
    }
}
