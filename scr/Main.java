//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main()
{
    Scanner in = new Scanner(System.in);

    Restaurant restaurant = new Restaurant();

    int choice;
    String idOrder ;
    String idItem;
    String name;

    do {

        System.out.println("\n========= Restaurant SYSTEM =========");
        System.out.println("1. Add Menu Item");
        System.out.println("2. Remove Menu Item");
        System.out.println("3. Display Menu");
        System.out.println("4. Search Menu Item");
        System.out.println("5. Create Order");
        System.out.println("6. Add Item to Order");
        System.out.println("7. Remove Item from Order");
        System.out.println("8. Display Order");
        System.out.println("9. Add Order to Kitchen Queue");
        System.out.println("10. Process Next Order");
        System.out.println("11. Search Order");
        System.out.println("12. Check Order Status");
        System.out.println("13. Display Completed Orders");
        System.out.println("14. Cancel Order");
        System.out.println("15. Exit");
        System.out.print("Choice : ");

        choice = in.nextInt();
        in.nextLine();

        switch (choice) {

            case 1:

                System.out.print(" the item's Name : ");
                name = in.nextLine();

                System.out.print("the item's ID : ");
                idItem = in.nextLine();

                System.out.print("the item's Price : ");
                double price = in.nextDouble();
                in.nextLine();

                System.out.print("the item's Category : ");
                String category= in.nextLine();
                MenuItem menuItem =
                        new MenuItem(idItem,name, price,category );
                if (restaurant.addMenuItem(menuItem))
                {
                    restaurant.itemList.add(menuItem);
                    System.out.println("Item is added.");
                }

                else
                    System.out.println("Failed,the id of item is found ");

                break;

            case 2:

                System.out.print("Item ID : ");
                idItem = in.nextLine();

                if (restaurant.removeMenuItem(idItem))
                    System.out.println("Item is removed.");
                else
                    System.out.println("Failed,the id of item is not  found ");

                break;

            case 3:
                System.out.println(restaurant.itemList);
                break;

            case 4:

                System.out.print("Item ID : ");
                idItem = in.nextLine();
                if (restaurant.searchMenuItem(idItem)==null)
                    System.out.println("the item is   found");
                else {
                    System.out.println("the item is not  found ");
                    System.out.println(restaurant.searchMenuItem(idItem));
                }
                break;

            case 5:

                System.out.print(" the customer's Name : ");
                name = in.nextLine();

                System.out.print("the order's ID : ");
                idOrder = in.nextLine();

                Order order=new Order(idOrder,name);
                if(restaurant.createOrder(order))
                    System.out.println(" the order is created ");
                else
                    System.out.println(" the id of order is found ");
                break;

            case 6:
                in.nextLine();
                System.out.print("the order's ID : ");
                idOrder = in.nextLine();
                System.out.print("the item's ID : ");
                idItem = in.nextLine();
                System.out.print("the item's quantity : ");
                int quantity = in.nextInt();
                in.nextLine();
                if(restaurant.addItem(idOrder,idItem,quantity)) {
                    System.out.println(" the item is added ");
                }
                else
                    System.out.println(" the item is not added ");
                break;

            case 7:
                System.out.print("the order's ID : ");
                idOrder = in.nextLine();
                System.out.print("the item's ID : ");
                idItem = in.nextLine();
                if(restaurant.removeItem(idOrder, idItem))
                {
                    System.out.println(" the item is removed ");
                }
                else
                    System.out.println(" the item is not removed");

                break;

            case 9:
                System.out.print("the order's ID : ");
                idOrder = in.nextLine();

                if(restaurant.addKitchen(idOrder))
                    System.out.println(" The order is being prepared in the kitchen. ");
                else
                    System.out.println(" The order is not  being prepared in the kitchen. ");
                break;

            case 10:
                System.out.print("the order's ID : ");
                idOrder = in.nextLine();

                if(restaurant.processNextOrder(idOrder))
                    System.out.println(" This order is processed ");
                else
                    System.out.println(" This order cannot be processed ");

                break;
            case 11,8:
                System.out.print("the order's ID : ");
                idOrder = in.nextLine();

                if(restaurant.searchOrder(idOrder))
                {
                    System.out.println(" this order is found \n     Order :  " );
                    System.out.println(restaurant.orders.get(idOrder));
                }
                else
                    System.out.println(" this order is not found");
                break;

            case 12:
                System.out.print("the order's ID : ");
                idOrder = in.nextLine();

                if (restaurant.checkOrder(idOrder)==OrderStatus.PENDING)
                    System.out.println("  This order is pending ");
                else if (restaurant.checkOrder(idOrder)==OrderStatus.COMPLETED)
                    System.out.println(" This order is completed ");
                else if (restaurant.checkOrder(idOrder)==OrderStatus.CANCELLED)
                    System.out.println(" This order is cancelled ");
                else if (restaurant.checkOrder(idOrder)==OrderStatus.IN_KITCHEN)
                    System.out.println(" this order is not  being prepared in the kitchen. ");
                else
                    System.out.println(" This order is not found");

                break;
            case 13:
                System.out.println(restaurant.ordersCompleted);
            case 14:
                System.out.print("the order's ID : ");
                idOrder = in.nextLine();
                if(restaurant.cancelOrder(idOrder))
                    System.out.println(" this order is canceled");
                else
                    System.out.println("This order cannot be canceled");
                break;

            case 15:
                System.out.println(" Wishing you a wonderful experience.");
                break;
            default:

                System.out.println("Invalid Choice.");

        }

    }
    while (choice != 15);
}
