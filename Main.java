import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    static Scanner scan = new Scanner(System.in);

    static ArrayList<String> product = new ArrayList<String>();
    static ArrayList<Integer> quantity = new ArrayList<Integer>();
    static ArrayList<Double> price = new ArrayList<Double>();

    static ArrayList<String> fullNameAR = new ArrayList<>();
    static ArrayList<Integer> ageAR = new ArrayList<>();
    static ArrayList<String> addressAR = new ArrayList<>();
    static ArrayList<String> emailAR = new ArrayList<>();
    static ArrayList<String> usernameAR = new ArrayList<>();
    static ArrayList<String> passwordAR = new ArrayList<>();

    static int indexNumber = 0;
    static String fullName;
    static int age = 0;
    static String address;
    static String email;
    static String username;
    static String password;
    static boolean usernameConfirmation = false;
    static boolean passwordConfirmation = false;

    static double originalTotalValue;
    static double finalGrandTotalValue;
    static double paymentReceived;
    static double changeGiven;
    static double discountAppliedValue;
    static double grandTotal;
    static String hasCoupon = null;
    static double discountPercent;
    static double payment;

    public static void login() {

        System.out.println("\n========================================================");
        System.out.println("                 Log in to your Account  ");
        System.out.println("========================================================");

        scan.nextLine();

        System.out.println("\n--------------------------------------------------------");
        System.out.print("Username: ");
        username = scan.nextLine();

        System.out.println("--------------------------------------------------------");
        System.out.print("Password: ");
        password = scan.nextLine();
        System.out.println("========================================================");

        for (int i = 0; i < usernameAR.size(); i++) {
            if (usernameAR.get(i).equals(username) && passwordAR.get(i).equals(password)) {
                usernameConfirmation = true;
                passwordConfirmation = true;
                indexNumber = i;
                break;
            }
        }

        if (usernameConfirmation && passwordConfirmation) {
            System.out.println("\n========================================================");
            System.out.println("     You have successfully logged into your account.    ");
            System.out.println("========================================================");

            fullName = fullNameAR.get(indexNumber);
            age = ageAR.get(indexNumber);
            address = addressAR.get(indexNumber);
            email = emailAR.get(indexNumber);
            username = usernameAR.get(indexNumber);
            password = passwordAR.get(indexNumber);

        } else {
            System.out.println("\n--------------------------------------------------------");
            System.out.println(" We couldn't log you in. Double-check your username and\npassword, or create a new account if you don't have one.  ");
            System.out.println("========================================================");
        }

    }

    public static void sign_up() {
        boolean emailMatch = false;
        boolean usernameMatch = false;
        boolean passwordMatch = false;

        scan.nextLine();

        System.out.println();
        System.out.println("========================================================");
        System.out.println("                   Create Your Account                 ");
        System.out.println("========================================================");

        System.out.println("\n--------------------------------------------------------");
        System.out.println("\t\t\t Step 1 of 2: Personal Details");
        System.out.println("--------------------------------------------------------");

        while (true) {
            try {
                System.out.print("> Full Name (First M.I. Last): ");
                fullName = scan.nextLine();

                Pattern fullNamePattern = Pattern.compile("^[A-Z][a-z]+\\s([A-Z]\\.|[A-Z]\\.[A-Z]\\.)\\s[A-Z][a-z]+$");
                Matcher fullNameMatcher = fullNamePattern.matcher(fullName);

                if (fullNameMatcher.matches()) {
                    fullNameAR.add(fullName);
                    break;
                } else {
                    throw new IllegalArgumentException("\n* Invalid full name format. Example: Juan D. Cruz\n");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("--------------------------------------------------------");

        while (true) {
            try {
                System.out.print("> Age: ");
                String input = scan.nextLine();
                age = Integer.parseInt(input);

                if (age < 1 || age > 120) throw new NumberFormatException("* Age must be between 1 and 120.");
                ageAR.add(age);
                break;
            } catch (NumberFormatException e) {
                System.out.println("\n* Invalid age. Please enter a valid integer between 1 and 120.\n");
            }
        }

        System.out.println("--------------------------------------------------------");

        while (true) {
            try {
                System.out.print("> Address (Brgy/Mun/Prov): ");
                address = scan.nextLine();

                Pattern addressPattern = Pattern.compile("^[A-Za-z.\\s]+,\\s*[A-Za-z.\\s]+,\\s*[A-Za-z.\\s]+$");
                Matcher addressMatcher = addressPattern.matcher(address);

                if (addressMatcher.matches()) {
                    addressAR.add(address);
                    break;
                } else {
                    throw new IllegalArgumentException("\n* Invalid address format. Example: Brgy. Malinis, San Juan, Batangas\n");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("--------------------------------------------------------");

        do {
            System.out.print("> Email Address: ");
            email = scan.nextLine();

            Pattern emailRegex = Pattern.compile("^[a-zA-Z0-9._\\-]+@[a-zA-Z0-9\\-.]+\\.[a-zA-Z]{2,}$", Pattern.CASE_INSENSITIVE);
            Matcher emailMatcher = emailRegex.matcher(email);
            emailMatch = emailMatcher.find();

            if (emailMatch) {
                emailAR.add(email);
            } else {
                System.out.println("\n* Invalid email format. Example: name@example.com\n");
            }

        } while (!emailMatch);

        System.out.println("--------------------------------------------------------");
        System.out.println("           Personal Details Process Completed!");
        System.out.println("========================================================");

        System.out.println("\n--------------------------------------------------------");
        System.out.println("\t\t     Step 2 of 2: Login Credentials");
        System.out.println("--------------------------------------------------------");

        do {
            System.out.println(" * Username must be 5-15 characters long.");
            System.out.println(" * Username can contain letters (A-Z, a-z), numbers (0-9)\nand/or special characters (._-).");
            System.out.print("\n> Enter your username: ");
            username = scan.nextLine();

            Pattern usernameRegex = Pattern.compile("^[a-zA-Z0-9_.-]{5,15}$", Pattern.CASE_INSENSITIVE);
            Matcher usernameMatcher = usernameRegex.matcher(username);
            usernameMatch = usernameMatcher.find();

            if (usernameMatch) {
                usernameAR.add(username);
                break;
            }

            System.out.println("");

        } while (!usernameMatch);

        System.out.println("--------------------------------------------------------");

        do {
            System.out.println(" * Password must be 6-18 characters long.");
            System.out.println(" * Password must include a lowercase letter, uppercase \nletter, number, and special character (!@#$%^&*-_.).");
            System.out.print("\n> Enter your password: ");
            password = scan.nextLine();

            Pattern passwordRegex = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*\\-_.])[A-Za-z\\d!@#$%^&*\\-_.]{6,18}$");
            Matcher passwordMatcher = passwordRegex.matcher(password);
            passwordMatch = passwordMatcher.find();

            if (passwordMatch) {
                passwordAR.add(password);
                break;
            }

            System.out.println("");

        } while (!passwordMatch);

        System.out.println("--------------------------------------------------------");
        System.out.println("           Login information successfully set!");
        System.out.println("========================================================");
    }

    public static void menu() {
        System.out.println("\n=======================================");
        System.out.println("             PRODUCT MENU");
        System.out.println("=======================================");
        System.out.println("  ID   | Product Name        | Price");
        System.out.println("---------------------------------------");
        System.out.println("  101  | Apple               | $0.99");
        System.out.println("  102  | Banana              | $0.79");
        System.out.println("  103  | Orange              | $1.29");
        System.out.println("  104  | Milk                | $3.49");
        System.out.println("  105  | Bread               | $2.99");
        System.out.println("  106  | Eggs                | $4.99");
        System.out.println("  107  | Chicken             | $8.99");
        System.out.println("  108  | Rice                | $12.99");
        System.out.println("  109  | Coffee              | $5.49");
        System.out.println("  110  | Chips               | $1.99");
        System.out.println("=======================================\n");
    }

    public static void prices() {
        price.clear();

        for (int i = 0; i < product.size(); i++) {

            if (product.get(i).equalsIgnoreCase("Apple")) {
                price.add(0.99);
            } else if (product.get(i).equalsIgnoreCase("Banana")) {
                price.add(0.79);
            } else if (product.get(i).equalsIgnoreCase("Orange")) {
                price.add(1.29);
            } else if (product.get(i).equalsIgnoreCase("Milk")) {
                price.add(3.49);
            } else if (product.get(i).equalsIgnoreCase("Bread")) {
                price.add(2.99);
            } else if (product.get(i).equalsIgnoreCase("Eggs")) {
                price.add(4.99);
            } else if (product.get(i).equalsIgnoreCase("Chicken")) {
                price.add(8.99);
            } else if (product.get(i).equalsIgnoreCase("Rice")) {
                price.add(12.99);
            } else if (product.get(i).equalsIgnoreCase("Coffee")) {
                price.add(5.49);
            } else if (product.get(i).equalsIgnoreCase("Chips")) {
                price.add(1.99);
            }
        }
    }

    public static void display() {

        grandTotal = 0;
        System.out.println("\n==================================================================");
        System.out.println("Product\t\t\tQuantity\t\tUnit Price\t\t\tTotal Price");
        System.out.println("------------------------------------------------------------------");

        for (int i = 0; i < product.size(); i++) {
            double totalPrice = quantity.get(i) * price.get(i);
            System.out.printf("%s\t\t\t%d\t\t\t\t$%.2f\t\t\t\t$%.2f\n", product.get(i), quantity.get(i), price.get(i), totalPrice);
            grandTotal += totalPrice;
        }
        System.out.println("------------------------------------------------------------------");
        System.out.printf("\t\t\t\t\t  Grand Total: $%.2f\n", grandTotal);
        System.out.println("==================================================================\n");
    }

    public static void add() {
        System.out.println("\n                    Add Product to Cart");
        System.out.println("========================================================");

        String addProduct = "";
        boolean isValid = false;

        while (!isValid) {
            System.out.print("> Enter Product Name: ");
            addProduct = scan.nextLine();

            if (addProduct.equalsIgnoreCase("Apple") ||
                    addProduct.equalsIgnoreCase("Banana") ||
                    addProduct.equalsIgnoreCase("Orange") ||
                    addProduct.equalsIgnoreCase("Milk") ||
                    addProduct.equalsIgnoreCase("Bread") ||
                    addProduct.equalsIgnoreCase("Eggs") ||
                    addProduct.equalsIgnoreCase("Chicken") ||
                    addProduct.equalsIgnoreCase("Rice") ||
                    addProduct.equalsIgnoreCase("Coffee") ||
                    addProduct.equalsIgnoreCase("Chips")) {

                isValid = true;
            } else {
                System.out.println("\n* Invalid product. Please enter one from the menu.\n");
            }
        }

        product.add(addProduct);

        System.out.println("--------------------------------------------------------");

        while (true) {
            System.out.print("> Enter Quantity: ");
            try {
                int qty = Integer.parseInt(scan.nextLine());

                if (qty > 0) {
                    quantity.add(qty);
                    break;
                } else {
                    System.out.println("\n* Quantity must be more than 0.\n");
                }
            } catch (Exception e) {
                System.out.println("\n* Please enter a valid number.\n");
            }
        }

        System.out.println("========================================================");

        prices();

        System.out.println("\n+-------------------------------------------------+");
        System.out.println("| Successfully Added the Product to your Cart     |");
        System.out.print("| Now Displaying your Updated Cart...             |\n");
        System.out.println("+-------------------------------------------------+");

        display();

    }

    public static void remove() {

        if (!product.isEmpty()) {

            System.out.println("\n                Remove Product from Cart");
            System.out.println("========================================================");
            String removeProduct = null;

            while (!product.contains(removeProduct)) {

                System.out.print("> Enter Product Name to Remove: ");
                removeProduct = scan.nextLine();

                if (product.contains(removeProduct)) {
                    for (int i = 0; i < product.size(); i++) {
                        if (product.get(i).equalsIgnoreCase(removeProduct)) {
                            product.remove(i);
                            quantity.remove(i);
                            price.remove(i);
                        }
                    }
                    System.out.println("========================================================");

                    System.out.println("\n+-------------------------------------------------+");
                    System.out.println("| Successfully Removed the Product from your Cart |");
                    System.out.print("| Now Displaying your Updated Cart...             |\n");
                    System.out.println("+-------------------------------------------------+");
                    display();
                    break;

                } else {

                    System.out.println("\n* Invalid product. Please enter one from product added.\n");

                }
            }

        } else {
            System.out.println("\n+--------------------------------------------------------+");
            System.out.println("| There's nothing to remove, your cart is already empty! |");
            System.out.println("+--------------------------------------------------------+\n");
        }

    }

    public static void clear() {

        if (!product.isEmpty()) {

            product.clear();
            quantity.clear();
            price.clear();

            System.out.println("\n+------------------------------------------+");
            System.out.println("| All items removed, your cart is cleared! |");
            System.out.println("+------------------------------------------+\n");

        } else {
            System.out.println("\n+--------------------------------------------------------+");
            System.out.println("| There's nothing to clear, your cart is already empty!  |");
            System.out.println("+--------------------------------------------------------+\n");
        }
    }

    public static void payment() {
        if (grandTotal != 0) {
            System.out.println("\n==================================================================");
            System.out.println("\n                         Your Cart Summary ");
            display();

            System.out.println("------------------------------------------------------------------");

            String wantDiscountCoupon = "";
            while (true) {
                System.out.print("Do you want to use a discount coupon? (yes/no): ");
                wantDiscountCoupon = scan.nextLine();
                if (wantDiscountCoupon.equalsIgnoreCase("yes") || wantDiscountCoupon.equalsIgnoreCase("no")) {
                    break;
                } else {
                    System.out.println("\n* Please enter 'yes' or 'no'.\n");
                }
            }

            if (wantDiscountCoupon.equalsIgnoreCase("yes")) {
                while (true) {
                    System.out.print("\nDo you have a discount coupon? (yes/no): ");
                    hasCoupon = scan.nextLine();
                    if (hasCoupon.equalsIgnoreCase("yes") || hasCoupon.equalsIgnoreCase("no")) {
                        break;
                    } else {
                        System.out.println("\n* Please enter 'yes' or 'no'.");
                    }
                }

                if (hasCoupon.equalsIgnoreCase("yes")) {
                    while (true) {
                        System.out.print("\nEnter discount percentage (e.g., 10 for 10% off): ");
                        try {
                            discountPercent = Double.parseDouble(scan.nextLine());
                            if (discountPercent >= 0 && discountPercent <= 100) {
                                break;
                            } else {
                                System.out.println("\n* Discount percent must be between 0 and 100.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("\n* Please enter a valid number.");
                        }
                    }
                }

            } else {
                hasCoupon = "no";
            }

            String isSenior = "no";
            if (age >= 60) {
                System.out.println("\nA 10% discount will be applied for those who are a senior citizen.");
                isSenior = "yes";
            }

            System.out.println("------------------------------------------------------------------");
            System.out.println();

            originalTotalValue = grandTotal;

            grandTotal = discount(hasCoupon, isSenior, discountPercent, originalTotalValue);
            finalGrandTotalValue = grandTotal;
            discountAppliedValue = originalTotalValue - finalGrandTotalValue;

            System.out.println("\n------------------------------------------------------------------");

            while (true) {
                System.out.print("Enter Payment Amount: $");
                try {
                    payment = Double.parseDouble(scan.nextLine());
                    if (payment >= grandTotal) {
                        break;
                    } else {
                        System.out.println("\n* Insufficient payment. Amount must be at least $" + String.format("%.2f", grandTotal));
                        System.out.println(" ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\n* Please enter a valid number.\n");
                }
            }

            System.out.println();

            paymentReceived = payment;
            changeGiven = payment - grandTotal;

            System.out.printf("Payment Received: $%.2f\n", paymentReceived);
            System.out.println("------------------------------------------------------------------");
            System.out.printf("Change: $%.2f\n", changeGiven);
            System.out.println("==================================================================");

            String withReceipt = "";

            System.out.println("\n------------------------------------------------------------------");

            while (true) {
                System.out.print("Do you want a digital receipt? (yes/no): ");
                withReceipt = scan.nextLine();
                if (withReceipt.equalsIgnoreCase("yes") || withReceipt.equalsIgnoreCase("no")) {
                    break;
                } else {
                    System.out.println("\n* Please enter 'yes' or 'no'.");
                }
            }

            if (withReceipt.equalsIgnoreCase("yes")) {
                receipt();
            } else {
                System.out.println("\n==================================================================");
                System.out.println("                  Thank you for shopping with us!");
                System.out.println("                        Have a great day!");
                System.out.println("==================================================================\n");
            }

        } else {
            System.out.println("\n+----------------------------------------------------------------+");
            System.out.println("| Oops! Looks like you have not added anything to your cart yet. |");
            System.out.println("+----------------------------------------------------------------+\n");
        }
    }

    public static double discount(String hasCoupon, String isSenior, double discountPercent, double originalTotal) {
        double discountedTotal = originalTotal;

        System.out.println("==================================================================");
        if (hasCoupon.equalsIgnoreCase("yes") && isSenior.equalsIgnoreCase("yes")) {
            discountedTotal -= discountedTotal * ((discountPercent + 10) / 100);
            System.out.print("Total Amount Due (w/ Coupon and Senior Citizen Discount): ");
        } else if (hasCoupon.equalsIgnoreCase("yes") && isSenior.equalsIgnoreCase("no")) {
            discountedTotal -= discountedTotal * (discountPercent / 100);
            System.out.print("Total Amount Due (w/ Coupon Discount): ");
        } else if (hasCoupon.equalsIgnoreCase("no") && isSenior.equalsIgnoreCase("yes")) {
            discountedTotal -= discountedTotal * 0.10;
            System.out.print("Total Amount Due (w/ Senior Citizen Discount): ");
        } else {
            System.out.print("Total Amount Due: ");
        }

        System.out.printf("$%.2f", discountedTotal);

        return discountedTotal;
    }

    public static void receipt() {
        System.out.println("\n****************************************");
        System.out.println("=======================================");
        System.out.println("\t\t   \"THE MARKET\" Online");
        System.out.println("=======================================");

        System.out.println("\t\t  Customer Information");
        System.out.println("---------------------------------------");
        System.out.printf("Name   : %s\n", fullName);
        System.out.printf("Email  : %s\n", email);
        System.out.printf("Age    : %d\n", age);
        System.out.printf("Address: %s\n", address);
        System.out.println("=======================================");

        System.out.println("Product\t\tQty\t\tPrice\t\tTotal");
        System.out.println("---------------------------------------");

        for (int i = 0; i < product.size(); i++) {
            double totalPrice = quantity.get(i) * price.get(i);
            System.out.printf("%-12s%d\t\t$%.2f\t\t$%.2f\n", product.get(i), quantity.get(i), price.get(i), totalPrice);
        }

        System.out.println("---------------------------------------");
        System.out.printf("Subtotal: \t\t\t$%.2f\n", originalTotalValue);
        System.out.printf("Discount Applied: \t$%.2f\n", discountAppliedValue);
        System.out.printf("Grand Total: \t\t$%.2f\n", finalGrandTotalValue);
        System.out.printf("Amount Paid: \t\t$%.2f\n", paymentReceived);
        System.out.printf("Change Given: \t\t$%.2f\n", changeGiven);
        System.out.println("=======================================");
        System.out.println("\tThank you for shopping with us!");
        System.out.println("\t      Have a great day!");
        System.out.println("=======================================");
        System.out.println("****************************************");
        System.out.println("\n------------------------------------------------------------------\n");
    }

    public static void editQuantity() {
        if (product.isEmpty()) {
            System.out.println("\n+----------------------------------------------+");
            System.out.println("| There's nothing to edit, your cart is empty! |");
            System.out.println("+----------------------------------------------+\n");
            return;
        }

        System.out.println("\n                  Edit Quantity in Cart");
        System.out.println("========================================================");

        boolean found = false;
        while (!found) {
            System.out.print("> Enter Product Name to Edit: ");
            String editProduct = scan.nextLine();

            for (int i = 0; i < product.size(); i++) {
                if (product.get(i).equalsIgnoreCase(editProduct)) {
                    int newQty = 0;
                    boolean validQty = false;
                    System.out.println("--------------------------------------------------------");
                    while (!validQty) {
                        System.out.print("> Enter New Quantity: ");
                        try {
                            newQty = Integer.parseInt(scan.nextLine());
                            if (newQty > 0) {
                                validQty = true;
                            } else {
                                System.out.println("\n* Quantity must be more than 0.\n");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("\n* Please enter a valid number.\n");
                        }
                    }

                    System.out.println("========================================================");

                    quantity.set(i, newQty);
                    prices();
                    System.out.println("\n+-------------------------------------------------+");
                    System.out.println("| Successfully Updated the Product Quantity       |");
                    System.out.println("| Now Displaying your Updated Cart...             |");
                    System.out.println("+-------------------------------------------------+");
                    display();
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("\n* Invalid product. Please enter one from product added.\n");
            }
        }
    }

    public static void transactionRecord() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        File theFile = new File("transactions.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(theFile, true))) {
            writer.write("\n==================================================================");
            writer.write("\n\t\t\t            \"THE MARKET\" Online");
            writer.write("\n==================================================================");
            writer.write("\n\t\t\t      Date & Time: " + formattedDateTime);
            writer.write("\n------------------------------------------------------------------");
            writer.write("\n\t\t\t           Customer Information");
            writer.write("\n------------------------------------------------------------------");
            writer.write("\n Logged in User: " + username);
            writer.write("\n------------------------------------------------------------------");
            writer.write(String.format("\n Name   : %s", fullName));
            writer.write(String.format("\n Email  : %s", email));
            writer.write(String.format("\n Age    : %d", age));
            writer.write(String.format("\n Address: %s", address));
            writer.write("\n==================================================================");
            writer.write("\nProduct\t\t\tQuantity\t\tUnit Price\t\t\tTotal Price");
            writer.write("\n------------------------------------------------------------------");

            for (int i = 0; i < product.size(); i++) {
                double totalPrice = quantity.get(i) * price.get(i);
                writer.write(String.format("\n%s\t\t\t%d\t\t\t\t$%.2f\t\t\t\t$%.2f", product.get(i), quantity.get(i), price.get(i), totalPrice));
            }

            writer.write("\n-----------------------------------------------------------------");
            writer.write(String.format("\n Subtotal: \t\t\t$%.2f", originalTotalValue));
            writer.write(String.format("\n Discount Applied: \t$%.2f", discountAppliedValue));
            writer.write(String.format("\n Grand Total: \t\t$%.2f", finalGrandTotalValue));
            writer.write(String.format("\n Amount Paid: \t\t$%.2f", paymentReceived));
            writer.write(String.format("\n Change Given: \t\t$%.2f", changeGiven));
            writer.write("\n==================================================================\n  ");

        } catch (IOException e) {
            System.out.println("\nFailed to update the file.");
            e.printStackTrace();
        }
    }

    public static void viewTransactionHistory() {
        File theFile = new File("transactions.txt");

        if (!theFile.exists()) {
            System.out.println("\n+------------------------------------------------------------+");
            System.out.println("| You don't have any transaction history yet.               |");
            System.out.println("| Make a purchase first to view your transaction records.   |");
            System.out.println("+------------------------------------------------------------+\n");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(theFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("\nFailed to read from the transaction file.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        int accountAction = 0;
        int action = 0;
        String again = "";
        String accountAgain = "";

        do {

            System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(">      Welcome to \"THE MARKET\" Online     >");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            do {
                System.out.println("\nIn order to access our services:");
                System.out.println(" [1] Log in to an existing account");
                System.out.println(" [2] Sign-up to create an account");

                try {
                    System.out.print("What would you like to do? #");
                    accountAction = scan.nextInt();

                    switch (accountAction) {
                        case 1:
                            login();
                            break;
                        case 2:
                            sign_up();
                            break;
                        default:
                            System.out.println("\n+---------------------------------------------------------------+");
                            System.out.println("| Hmmm...that is not on the list. Please select a valid option. |");
                            System.out.println("+---------------------------------------------------------------+");
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("\n+-------------------------------+");
                    System.out.println("| Please select a valid option. |");
                    System.out.println("+-------------------------------+");

                    scan.nextLine();
                }

            } while (!usernameConfirmation && !passwordConfirmation);

            do {

                usernameConfirmation = false;
                passwordConfirmation = false;

                System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                System.out.println(">      Welcome to \"THE MARKET\" Online     >");
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

                System.out.println("\n-------------------------------------------");
                System.out.println(" Currently Logged in as: " + username);
                System.out.println("-------------------------------------------");

                menu();

                do {

                    System.out.println("Hello, " + fullName + "! What would you like to do?");
                    System.out.println(" [1] Add a Product");
                    System.out.println(" [2] Remove a Product");
                    System.out.println(" [3] Edit Quantity of a Product");
                    System.out.println(" [4] Show Menu");
                    System.out.println(" [5] Clear Cart");
                    System.out.println(" [6] Proceed to Payment");
                    System.out.println(" [7] View Transaction History");
                    System.out.println(" [8] Cancel Transaction");

                    try {
                        System.out.print("What would you like to do? #");
                        action = scan.nextInt();

                        if (action < 1 || action > 8) {

                            System.out.println("\n+---------------------------------------------------------------+");
                            System.out.println("| Hmmm...that is not on the list. Please select a valid number. |");
                            System.out.println("+---------------------------------------------------------------+\n");

                        }
                    } catch (Exception e) {

                        System.out.println("\n+-------------------------------+");
                        System.out.println("| Please select a valid option. |");
                        System.out.println("+-------------------------------+\n");
                    }

                    scan.nextLine();

                    switch (action) {
                        case 1 -> add();
                        case 2 -> remove();
                        case 3 -> editQuantity();
                        case 4 -> menu();
                        case 5 -> clear();
                        case 6 -> {
                            payment();
                            transactionRecord();
                            product.clear();
                            quantity.clear();
                            price.clear();
                        }
                        case 7 -> {
                            System.out.println("\n\t\t\t             Transaction History");
                            viewTransactionHistory();
                        }
                    }

                } while (action != 8);

                if (action == 6) {
                    System.out.println("\nDo you want to perform another transaction? (Yes/No): ");
                    System.out.println(" [1] Yes, continue shopping.");
                    System.out.println(" [Other Keys] No, exit the program.");
                    System.out.print("What would you like to do? #");
                    again = scan.nextLine();
                }

                product.clear();
                quantity.clear();
                price.clear();

            } while (again.equals("1"));

            System.out.println("\nThanks for visiting! We hope you had fun and will be\nback for more! Would you like to:");
            System.out.println(" [1] Sign out of your account");
            System.out.println(" [Other Keys] Exit the program");
            System.out.print("What would you like to do? #");
            accountAgain = scan.nextLine();

        } while (accountAgain.equals("1"));

    }
}
