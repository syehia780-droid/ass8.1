import java.util.ArrayList;
import java.util.List;
public class Order {
    private String orderId;
    private String custumerName;
   protected List<OrderItem> itemList;
   protected OrderStatus status;
   private double total=0;
   protected int numItem=0;

    public Order(String orderId, String custumerName) {
        this.orderId = orderId;
        itemList=new ArrayList<>();
        this.itemList = itemList;
         status=OrderStatus.PENDING;
    }

    public int getNumItem() {
        return numItem;
    }

    public void setNumItem(int numItem) {
        this.numItem = numItem;
    }

    public Order(String orderId, OrderItem orderItem) {
        this.orderId = orderId;
        itemList.add(orderItem);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public String getCustumerName() {
        return custumerName;
    }

    public void setCustumerName(String custumerName) {
        this.custumerName = custumerName;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", custumerName='" + custumerName + '\'' +
                ", itemList=" + itemList +
                ", total=" + total +
                ", status=" + status +
                '}';
    }
    public double calTotal()
    {
        itemList.forEach(item->{total+=item.calSubTotal();});
        if(total!=0)
            numItem++;
        return total;
    }

}
