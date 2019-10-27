/*    */ package thaumcraft.common.items.baubles;
/*    */ 
/*    */ import baubles.api.BaubleType;
/*    */ import baubles.api.BaublesApi;
/*    */ import baubles.api.IBauble;
/*    */ import baubles.api.cap.IBaublesItemHandler;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.NonNullList;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.util.text.translation.I18n;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.items.RechargeHelper;
/*    */ import thaumcraft.common.items.ItemTCBase;
/*    */ 
/*    */ public class ItemAmuletVis
/*    */   extends ItemTCBase
/*    */   implements IBauble
/*    */ {
/*    */   public ItemAmuletVis() {
/* 27 */     super("amulet_vis", new String[] { "found", "crafted" });
/* 28 */     this.field_77777_bU = 1;
/* 29 */     func_77656_e(0);
/* 30 */     func_77627_a(true);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public EnumRarity func_77613_e(ItemStack itemstack) { return (itemstack.func_77952_i() == 0) ? EnumRarity.UNCOMMON : EnumRarity.RARE; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public BaubleType getBaubleType(ItemStack itemstack) { return BaubleType.AMULET; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
/* 45 */     if (player instanceof EntityPlayer && !player.field_70170_p.field_72995_K && player.field_70173_aa % (
/* 46 */       (itemstack.func_77952_i() == 0) ? 40 : 5) == 0) {
/*    */       
/* 48 */       NonNullList<ItemStack> inv = ((EntityPlayer)player).field_71071_by.field_70462_a;
/* 49 */       for (int a = 0; a < ((EntityPlayer)player).field_71071_by.func_70451_h(); a++) {
/* 50 */         if (RechargeHelper.rechargeItem(player.field_70170_p, (ItemStack)inv.get(a), player.func_180425_c(), (EntityPlayer)player, 1) > 0.0F) {
/*    */           return;
/*    */         }
/*    */       } 
/*    */       
/* 55 */       IBaublesItemHandler baubles = BaublesApi.getBaublesHandler((EntityPlayer)player);
/* 56 */       for (int a = 0; a < baubles.getSlots(); a++) {
/* 57 */         if (RechargeHelper.rechargeItem(player.field_70170_p, baubles.getStackInSlot(a), player
/* 58 */             .func_180425_c(), (EntityPlayer)player, 1) > 0.0F) {
/*    */           return;
/*    */         }
/*    */       } 
/*    */ 
/*    */       
/* 64 */       inv = ((EntityPlayer)player).field_71071_by.field_70460_b;
/* 65 */       for (int a = 0; a < inv.size(); a++) {
/* 66 */         if (RechargeHelper.rechargeItem(player.field_70170_p, (ItemStack)inv.get(a), player.func_180425_c(), (EntityPlayer)player, 1) > 0.0F) {
/*    */           return;
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/* 75 */   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) { tooltip.add(TextFormatting.AQUA + I18n.func_74838_a("item.amulet_vis.text")); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\baubles\ItemAmuletVis.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */