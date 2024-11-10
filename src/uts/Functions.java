package uts; // 2410101020 - Kirandana Arkanalla

import java.util.ArrayList;
import java.util.Scanner;

public class Functions {
    public Scanner scan = new Scanner(System.in);
    public ArrayList<Item> items = new ArrayList<>();
    public ArrayList<Order> orders = new ArrayList<>();
    public int itemCounter = 1;

    public void homeMenu() {
        System.out.println("\nHalaman Utama");
        System.out.println("1. Barang");
        System.out.println("2. Pesanan");
        System.out.println("3. Keluar");
    }

    public void quit() {
        System.out.println("Anda telah keluar dari program.");
    }

    public void error() {
        System.out.println("Input tidak dikenali.");
    }
    
    // FUNGSI-FUNGSI MENU ITEM
    
    public void itemMenu() {
        System.out.println("\nHalaman Barang");
        System.out.println("1. Tambah");
        System.out.println("2. Perbarui");
        System.out.println("3. Hapus");
        System.out.println("4. Mencari");
        System.out.println("5. List");
        System.out.println("6. Balik ke Halaman Utama");
    }

    public void itemsMenuNavigation() {
        while (true) {
            itemMenu();
            System.out.print("Pilih opsi: ");
            int itemChoice = scan.nextInt();

            switch (itemChoice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    updateItem();
                    break;
                case 3:
                    deleteItem();
                    break;
                case 4:
                    searchItem();
                    break;
                case 5:
                    listAllItems();
                    break;
                case 6:
                    return;
                default:
                    error();
                    break;
            }
        }
    }

    public void addItem() {
        System.out.print("\nTulis nama barang: ");
        scan.nextLine();
        String name = scan.nextLine();
        System.out.print("\nTulis harga barang: ");
        double price = scan.nextDouble();

        Item newItem = new Item(itemCounter++, name, price);
        items.add(newItem);
        System.out.println("\nBarang telah ditambahkan!");
    }

    public void deleteItem() {
        System.out.print("\nInput ID Barang yang ingin dihapus: ");
        int id = scan.nextInt();
        Item itemToDelete = null;

        for (Item item : items) {
            if (item.getId() == id) {
                itemToDelete = item;
                break;
            }
        }

        if (itemToDelete != null) {
            items.remove(itemToDelete);
            System.out.println("\nBarang berhasil dihapus!");
        } else {
            System.out.println("\nBarang dengan ID " + id + " tidak ditemukan.");
        }
    }

    public void searchItem() {
        System.out.print("\nTulis nama barang yang dicari: ");
        scan.nextLine();
        String searchName = scan.nextLine();
        boolean found = false;

        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(searchName)) {
                System.out.println(item);
                found = true;
            }
        }

        if (!found) {
            System.out.println("\nTidak ada barang dengan nama: " + searchName);
        }
    }

    public void updateItem() {
        System.out.print("\nTulis ID barang yang ingin diperbarui: ");
        int id = scan.nextInt();
        Item itemToUpdate = null;

        for (Item item : items) {
            if (item.getId() == id) {
                itemToUpdate = item;
                break;
            }
        }

        if (itemToUpdate != null) {
            System.out.print("\nTulis nama baru untuk barang: ");
            scan.nextLine();
            String newName = scan.nextLine();
            System.out.print("\nTulis harga baru untuk barang: ");
            double newPrice = scan.nextDouble();

            itemToUpdate.setName(newName);
            itemToUpdate.setPrice(newPrice);
            System.out.println("\nBarang berhasil diperbarui!");
        } else {
            System.out.println("\nBarang dengan ID " + id + " tidak ditemukan.");
        }
    }

    public void listAllItems() {
        if (items.isEmpty()) {
            System.out.println("\nTidak ada barang untuk di display.");
        } else {
            System.out.println("\nList semua barang:");
            for (Item item : items) {
                System.out.println(item);
            }
        }
    }
    
// FUNGSI-FUNGSI PESANAN MENU
    
    public void pesananMenu() {
        System.out.println("\nHalaman Pesanan");
        System.out.println("1. Pesan Barang");
        System.out.println("2. Barang Pending");
        System.out.println("3. Kembali ke halaman utama");
    }
    
    public void pesanBarang() {
        scan.nextLine();
        System.out.print("\nBarang yang dipesan: ");
        String pesanBarang = scan.nextLine();

        System.out.print("\nKuantitas: ");
        int quantity = scan.nextInt();

        orders.add(new Order(pesanBarang, quantity));
        System.out.println(pesanBarang + " sebanyak " + quantity + " telah ditambahkan!");
    }

    public void pendingBarang() {
        if (orders.isEmpty()) {
            System.out.println("\nTidak ada barang yang pending.");
        } else {
            System.out.println("\nDaftar Barang Pending:");
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

    public void pesananMenuChoice() {
        while (true) {
            pesananMenu();
            System.out.print("Pilih opsi: ");
            int salesChoice = scan.nextInt();

            switch (salesChoice) {
                case 1:
                    pesanBarang();
                    break;
                case 2:
                    pendingBarang();
                    break;
                case 3:
                    return;
                default:
                    error();
                    break;
            }
        }
    }

    public void run() {
        while (true) {
            homeMenu();
            System.out.print("Pilih opsi: ");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    itemsMenuNavigation();
                    break;
                case 2:
                    pesananMenuChoice();
                    break;
                case 3:
                    quit();
                    return;
                default:
                    error();
                    break;
            }
        }
    }
    
// CLASS UNTUK FUNGSI PENDING BARANG

    public class Order {
        private String itemName;
        private int quantity;

        public Order(String itemName, int quantity) {
            this.itemName = itemName;
            this.quantity = quantity;
        }

        public String getItemName() {
            return itemName;
        }

        public int getQuantity() {
            return quantity;
        }

        public String toString() {
            return "Barang: " + itemName + ", Kuantitas: " + quantity;
        }
    }
    
// CLASS UNTUK FUNGSI ITEM
    
    public class Item {
        private int id;
        private String name;
        private double price;

        public Item(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(double price) {
            this.price = price;
        }
        
        public String toString() {
            return "Item ID: " + id + ", Name: " + name + ", Price: Rp. " + price;
        }
    }
    


    public static void main(String[] args) {
        Functions app = new Functions();
        app.run();
    }
}