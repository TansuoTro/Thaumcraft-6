/*    */ package thaumcraft.api.items;
/*    */ 
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.NonNullList;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemGenericEssentiaContainer
/*    */   extends Item
/*    */   implements IEssentiaContainerItem
/*    */ {
/*    */   protected int base;
/*    */   
/*    */   public ItemGenericEssentiaContainer(int base) {
/* 30 */     this.base = 1;
/*    */     this.base = base;
/*    */     func_77625_d(64);
/*    */     func_77627_a(true);
/* 34 */     func_77656_e(0); } public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) { for (Aspect tag : Aspect.aspects.values()) {
/* 35 */       ItemStack i = new ItemStack(this);
/* 36 */       setAspects(i, (new AspectList()).add(tag, this.base));
/* 37 */       items.add(i);
/*    */     }  }
/*    */ 
/*    */ 
/*    */   
/*    */   public AspectList getAspects(ItemStack itemstack) {
/* 43 */     if (itemstack.func_77942_o()) {
/* 44 */       AspectList aspects = new AspectList();
/* 45 */       aspects.readFromNBT(itemstack.func_77978_p());
/* 46 */       return (aspects.size() > 0) ? aspects : null;
/*    */     } 
/* 48 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAspects(ItemStack itemstack, AspectList aspects) {
/* 53 */     if (!itemstack.func_77942_o())
/* 54 */       itemstack.func_77982_d(new NBTTagCompound()); 
/* 55 */     aspects.writeToNBT(itemstack.func_77978_p());
/*    */   }
/*    */ 
/*    */   
/* 59 */   public boolean ignoreContainedAspects() { return false; }
/*    */ 
/*    */   
/*    */   public void func_77663_a(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
/* 63 */     if (!world.field_72995_K && !stack.func_77942_o()) {
/* 64 */       Aspect[] displayAspects = (Aspect[])Aspect.aspects.values().toArray(new Aspect[0]);
/* 65 */       setAspects(stack, (new AspectList()).add(displayAspects[world.field_73012_v.nextInt(displayAspects.length)], this.base));
/*    */     } 
/* 67 */     super.func_77663_a(stack, world, entity, par4, par5);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_77622_d(ItemStack stack, World world, EntityPlayer player) {
/* 72 */     if (!world.field_72995_K && !stack.func_77942_o()) {
/* 73 */       Aspect[] displayAspects = (Aspect[])Aspect.aspects.values().toArray(new Aspect[0]);
/* 74 */       setAspects(stack, (new AspectList()).add(displayAspects[world.field_73012_v.nextInt(displayAspects.length)], this.base));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\items\ItemGenericEssentiaContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */