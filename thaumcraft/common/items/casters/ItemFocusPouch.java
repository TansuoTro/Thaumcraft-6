/*    */ package thaumcraft.common.items.casters;
/*    */ 
/*    */ import baubles.api.BaubleType;
/*    */ import baubles.api.IBauble;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.ItemStackHelper;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.NonNullList;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.Thaumcraft;
/*    */ import thaumcraft.common.items.ItemTCBase;
/*    */ 
/*    */ public class ItemFocusPouch
/*    */   extends ItemTCBase
/*    */   implements IBauble
/*    */ {
/*    */   public ItemFocusPouch() {
/* 24 */     super("focus_pouch", new String[0]);
/* 25 */     func_77625_d(1);
/* 26 */     func_77627_a(false);
/* 27 */     func_77656_e(0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public boolean func_77651_p() { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.UNCOMMON; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   public boolean func_77636_d(ItemStack stack1) { return false; }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand hand) {
/* 48 */     if (!worldIn.field_72995_K) {
/* 49 */       playerIn.openGui(Thaumcraft.instance, 5, worldIn, 
/*    */           
/* 51 */           MathHelper.func_76128_c(playerIn.field_70165_t), MathHelper.func_76128_c(playerIn.field_70163_u), MathHelper.func_76128_c(playerIn.field_70161_v));
/*    */     }
/* 53 */     return super.func_77659_a(worldIn, playerIn, hand);
/*    */   }
/*    */   
/*    */   public NonNullList<ItemStack> getInventory(ItemStack item) {
/* 57 */     NonNullList<ItemStack> stackList = NonNullList.func_191197_a(18, ItemStack.field_190927_a);
/* 58 */     if (item.func_77942_o()) {
/* 59 */       ItemStackHelper.func_191283_b(item.func_77978_p(), stackList);
/*    */     }
/* 61 */     return stackList;
/*    */   }
/*    */   
/*    */   public void setInventory(ItemStack item, NonNullList<ItemStack> stackList) {
/* 65 */     if (item.func_77978_p() == null) item.func_77982_d(new NBTTagCompound()); 
/* 66 */     ItemStackHelper.func_191282_a(item.func_77978_p(), stackList);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 71 */   public BaubleType getBaubleType(ItemStack itemstack) { return BaubleType.BELT; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onWornTick(ItemStack itemstack, EntityLivingBase player) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEquipped(ItemStack itemstack, EntityLivingBase player) {}
/*    */ 
/*    */   
/*    */   public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {}
/*    */ 
/*    */   
/* 85 */   public boolean canEquip(ItemStack itemstack, EntityLivingBase player) { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 90 */   public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) { return true; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\ItemFocusPouch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */