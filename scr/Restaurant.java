import javax.swing.*;
import java.util.*;
import java.util.function.Predicate;

public class Restaurant {
    protected List<MenuItem> itemList;
    protected List<Order> kitchenQueue;
    protected Map<String, Order> orders;
    protected Map<String, Order> ordersCompleted;

    public Restaurant() {
        itemList = new ArrayList<>();
        kitchenQueue = new LinkedList<>();
        orders = new HashMap<>();
        ordersCompleted = new HashMap<>();
    }

    public List<MenuItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<MenuItem> itemList) {
        this.itemList = itemList;
    }

    public List<Order> getKitchenQueue() {
        return kitchenQueue;
    }

    public void setKitchenQueue(List<Order> kitchenQueue) {
        this.kitchenQueue = kitchenQueue;
    }

    public Map<String, Order> getOrders() {
        return orders;
    }

    public void setOrders(Map<String, Order> orders) {
        this.orders = orders;
    }

    public Map<String, Order> getOrdersCompleted() {
        return ordersCompleted;
    }

    public void setOrdersCompleted(Map<String, Order> ordersCompleted) {
        this.ordersCompleted = ordersCompleted;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "itemList=" + itemList +
                ", orderList=" + kitchenQueue +
                ", orders=" + orders +
                ", ordersCompleted=" + ordersCompleted +
                '}';
    }

    public boolean addMenuItem(MenuItem menuItem )
    {

        Predicate<List<MenuItem>> listPredicate=(ob->ob.contains(menuItem));
        return  listPredicate.test(itemList);
    }

    public boolean removeMenuItem(String id) {
        final boolean[] checkId = {true};
        itemList.forEach(menuItem1->{
            if (!menuItem1.getId() .equals(id))
                checkId[0] = false;
            itemList.remove(menuItem1);
            checkId[0] = true;
        });
        return checkId[0];
    }

    public MenuItem searchMenuItem(String id) {
        for (MenuItem menuItem1 : itemList) {
            if (!menuItem1.getId() .equals(id))
                return null;
            return menuItem1;
        }
        return null;
    }

    public boolean createOrder(Order order) {
        Predicate<Map<String,Order>> listPredicate=(ob->{if(ob.containsKey(order.getOrderId()))
            return false;
            order.status = OrderStatus.PENDING;
            orders.put(order.getOrderId(), order);
            return true;
            });
        return  listPredicate.test(orders);

    }

    public boolean addItem(String orderId, String itemId, int quantity)
         {

            Order order = orders.get(orderId);

            if (order == null) {
                return false;
            }

            if (order.status == OrderStatus.CANCELLED ||
                    order.status == OrderStatus.COMPLETED) {
                return false;
            }

            for (OrderItem itemOrder : order.itemList) {

                if (itemOrder.item.id.equals(itemId)) {

                    itemOrder.setQuantity(quantity);

                    order.calTotal();

                    return true;
                }
            }

            return false;
        }



public boolean removeItem(String orderId, String itemId) {

    Order order = orders.get(orderId);

    if (order == null) {
        return false;
    }

    if (order.status == OrderStatus.CANCELLED ||
            order.status == OrderStatus.COMPLETED) {
        return false;
    }

    OrderItem item = order.itemList.stream()
            .filter(i -> i.item.id.equals(itemId))
            .findFirst()
            .orElse(null);

    if (item == null) {
        return false;
    }

    order.itemList.remove(item);
    order.calTotal();

    return true;
}
    public boolean addKitchen(String idOrder) {

        Order order = orders.get(idOrder);

        if (order == null) {
            return false;
        }

        if (order.status != OrderStatus.PENDING) {
            return false;
        }

        order.status = OrderStatus.IN_KITCHEN;
        kitchenQueue.add(order);

        return true;
    }
    public boolean processNextOrder(String idOrder) {

        for (Order order : kitchenQueue) {

            if (order.getOrderId().equals(idOrder) && order.numItem != 0) {

                order.status = OrderStatus.COMPLETED;

                kitchenQueue.remove(order);
                ordersCompleted.put(order.getOrderId(), order);

                return true;
            }
        }

        return false;
    }

    public OrderStatus checkOrder(String idOrder) {

        Order order = orders.get(idOrder);

        if (order == null) {
            return null;
        }

        return order.status;
    }


    public boolean searchOrder(String idOrder)
    {

        Order order = orders.get(idOrder);

        return order != null;
    }

    public boolean cancelOrder(String idOrder) {

        Order order = orders.get(idOrder);

        if (order == null) {
            return false;
        }

        if (order.status == OrderStatus.CANCELLED ||
                order.status == OrderStatus.COMPLETED) {
            return false;
        }

        if (order.status == OrderStatus.IN_KITCHEN ||
                order.status == OrderStatus.PENDING) {

            kitchenQueue.remove(order);
            order.status = OrderStatus.CANCELLED;

            return true;
        }

        return false;
    }
}
