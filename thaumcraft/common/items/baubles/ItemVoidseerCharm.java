/*    */ package thaumcraft.common.items.baubles;
/*    */ 
/*    */ import baubles.api.BaubleType;
/*    */ import baubles.api.IBauble;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.util.text.translation.I18n;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.capabilities.IPlayerWarp;
/*    */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*    */ import thaumcraft.api.items.IVisDiscountGear;
/*    */ import thaumcraft.api.items.IWarpingGear;
/*    */ import thaumcraft.common.items.ItemTCBase;
/*    */ 
/*    */ 
/*    */ public class ItemVoidseerCharm
/*    */   extends ItemTCBase
/*    */   implements IBauble, IVisDiscountGear, IWarpingGear
/*    */ {
/*    */   public ItemVoidseerCharm() {
/* 27 */     super("voidseer_charm", new String[0]);
/* 28 */     this.field_77777_bU = 1;
/* 29 */     this.canRepair = false;
/* 30 */     func_77656_e(0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.RARE; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public BaubleType getBaubleType(ItemStack itemstack) { return BaubleType.CHARM; }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
/* 46 */     tooltip.add(TextFormatting.DARK_BLUE + "" + TextFormatting.ITALIC + I18n.func_74838_a("item.voidseer_charm.text"));
/* 47 */     super.func_77624_a(stack, worldIn, tooltip, flagIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getVisDiscount(ItemStack stack, EntityPlayer player) {
/* 52 */     int q = 0;
/* 53 */     IPlayerWarp warp = ThaumcraftCapabilities.getWarp(player);
/* 54 */     if (warp != null) {
/* 55 */       int pw = Math.min(100, warp.get(IPlayerWarp.EnumWarpType.PERMANENT));
/* 56 */       q = (int)(pw / 100.0F * 25.0F);
/*    */     } 
/* 58 */     return q;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 63 */   public int getWarp(ItemStack itemstack, EntityPlayer player) { return getVisDiscount(itemstack, player) / 5; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\baubles\ItemVoidseerCharm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */