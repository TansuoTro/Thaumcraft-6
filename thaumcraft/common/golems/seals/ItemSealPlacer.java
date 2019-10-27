/*    */ package thaumcraft.common.golems.seals;
/*    */ 
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumActionResult;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.NonNullList;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.golems.ISealDisplayer;
/*    */ import thaumcraft.api.golems.seals.ISeal;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.items.ItemTCBase;
/*    */ 
/*    */ public class ItemSealPlacer
/*    */   extends ItemTCBase
/*    */   implements ISealDisplayer {
/*    */   public ItemSealPlacer() {
/* 23 */     super("seal", new String[] { "blank" });
/* 24 */     func_77625_d(64);
/* 25 */     func_77627_a(true);
/* 26 */     func_77656_e(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) {
/* 31 */     if (tab == ConfigItems.TABTC || tab == CreativeTabs.field_78027_g) {
/* 32 */       String[] vn = getVariantNames();
/* 33 */       for (int a = 0; a < vn.length; a++) {
/* 34 */         items.add(new ItemStack(this, 1, a));
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public String[] getVariantNames() {
/* 40 */     if (SealHandler.types.size() + 1 != this.VARIANTS.length) {
/* 41 */       String[] rs = SealHandler.getRegisteredSeals();
/* 42 */       String[] out = new String[rs.length + 1];
/* 43 */       out[0] = "blank";
/* 44 */       int indx = 1;
/* 45 */       for (String s : rs) {
/* 46 */         String[] sp = s.split(":");
/* 47 */         out[indx] = (sp.length > 1) ? sp[1] : sp[0];
/* 48 */         indx++;
/*    */       } 
/* 50 */       this.VARIANTS = out;
/* 51 */       this.VARIANTS_META = new int[this.VARIANTS.length];
/* 52 */       for (int m = 0; m < this.VARIANTS.length; m++) {
/* 53 */         this.VARIANTS_META[m] = m;
/*    */       }
/*    */     } 
/* 56 */     return this.VARIANTS;
/*    */   }
/*    */   
/*    */   public static ItemStack getSealStack(String sealKey) {
/* 60 */     String[] rs = SealHandler.getRegisteredSeals();
/* 61 */     int indx = 1;
/* 62 */     for (String s : rs) {
/* 63 */       if (s.equals(sealKey)) {
/* 64 */         return new ItemStack(ItemsTC.seals, 1, indx);
/*    */       }
/* 66 */       indx++;
/*    */     } 
/* 68 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
/* 76 */     if (world.field_72995_K || player.func_184586_b(hand).func_77952_i() == 0 || player.func_70093_af()) return EnumActionResult.PASS;
/*    */     
/* 78 */     if (!player.func_175151_a(pos, side, player.func_184586_b(hand))) return EnumActionResult.FAIL;
/*    */     
/* 80 */     String[] rs = SealHandler.getRegisteredSeals();
/*    */     
/* 82 */     ISeal seal = null;
/*    */     try {
/* 84 */       seal = (ISeal)SealHandler.getSeal(rs[player.func_184586_b(hand).func_77952_i() - 1]).getClass().newInstance();
/* 85 */     } catch (Exception e) {
/* 86 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 89 */     if (seal == null || !seal.canPlaceAt(world, pos, side)) return EnumActionResult.FAIL;
/*    */     
/* 91 */     if (SealHandler.addSealEntity(world, pos, side, seal, player) && 
/* 92 */       !player.field_71075_bZ.field_75098_d) player.func_184586_b(hand).func_190918_g(1);
/*    */     
/* 94 */     return EnumActionResult.SUCCESS;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 99 */   public boolean doesSneakBypassUse(ItemStack stack, IBlockAccess world, BlockPos pos, EntityPlayer player) { return true; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\seals\ItemSealPlacer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */