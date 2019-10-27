/*    */ package thaumcraft.common.items.curios;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.EnumActionResult;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.text.translation.I18n;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.items.ItemTCBase;
/*    */ import thaumcraft.common.lib.SoundsTC;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ 
/*    */ public class ItemLootBag
/*    */   extends ItemTCBase {
/*    */   public ItemLootBag() {
/* 23 */     super("loot_bag", new String[] { "common", "uncommon", "rare" });
/* 24 */     func_77625_d(16);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumRarity func_77613_e(ItemStack stack) {
/* 29 */     switch (stack.func_77952_i()) { case 1:
/* 30 */         return EnumRarity.UNCOMMON;
/* 31 */       case 2: return EnumRarity.RARE; }
/*    */     
/* 33 */     return EnumRarity.COMMON;
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
/* 39 */     super.func_77624_a(stack, worldIn, tooltip, flagIn);
/* 40 */     tooltip.add(I18n.func_74838_a("tc.lootbag"));
/*    */   }
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
/* 45 */     if (!world.field_72995_K) {
/* 46 */       int q = 8 + world.field_73012_v.nextInt(5);
/* 47 */       for (int a = 0; a < q; a++) {
/* 48 */         ItemStack is = Utils.generateLoot(player.func_184586_b(hand).func_77952_i(), world.field_73012_v);
/* 49 */         if (is != null && !is.func_190926_b()) {
/* 50 */           world.func_72838_d(new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, is.func_77946_l()));
/*    */         }
/*    */       } 
/*    */       
/* 54 */       player.func_184185_a(SoundsTC.coins, 0.75F, 1.0F);
/*    */     } 
/* 56 */     player.func_184586_b(hand).func_190918_g(1);
/* 57 */     return new ActionResult(EnumActionResult.SUCCESS, player.func_184586_b(hand));
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\curios\ItemLootBag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */