
import java.awt.*;
import javax.swing.*;
public class Item{
    String  itemCode;
   int originalPrice;
   int soldPrice;
   int number;
   int sold;
   int remain;
   int benefit;
   
   public Item()
   {
	   
   }
   
   public Item(String itemCode,int originalPrice,int soldPrice,int benefit)
   {
	   this.itemCode=itemCode;
	   this.originalPrice=originalPrice;
	   this.soldPrice=soldPrice;
	   this.benefit=benefit(this.soldPrice,this.originalPrice);
   }
   
   
   public Item(int number,int sold,int remain)
   {
	   this.number=number;
	   this.sold=sold;
	   this.remain=this.number-this.sold;
   }
   
   
  public int benefit(int soldPrice , int originalPrice){
   benefit = soldPrice-originalPrice;
    return benefit;
}
  
  

  
 public String getItemCode(){
           return itemCode;
      }
public void setItemCode(String itemCode){
         this.itemCode = itemCode;
     } 
public int getOriginalPrice(){
       return originalPrice;
   }
public void setOriginalPrice(int originalPrice){
      this.originalPrice = originalPrice;
}


public int getSoldPrice(){
     return soldPrice;
}
public  void setSoldPrice(int soldPrice){
      this.soldPrice = soldPrice;
}
public int getNoOfItem(){
    return number;
}
public void setNoOfItem(int number){
   this.number = number;
}
public int getSoldItem(){
  return sold;
}
public void setSoldItem(int sold){
 this.sold = sold;
}
public int getRemainItem(){
 return remain;
}
public void setRemainItem(int remain){
 this.remain = remain;
}
}





