/*     */ package thaumcraft.common.golems;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.EnumPacketDirection;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.util.FakePlayer;
/*     */ import net.minecraftforge.common.util.FakePlayerFactory;
/*     */ import thaumcraft.api.golems.IGolemAPI;
/*     */ import thaumcraft.common.lib.network.FakeNetHandlerPlayServer;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GolemInteractionHelper
/*     */ {
/*     */   public static void golemClick(World world, IGolemAPI golem, BlockPos pos, EnumFacing face, ItemStack clickStack, boolean sneaking, boolean rightClick) {
/*  34 */     FakePlayer fp = FakePlayerFactory.get((WorldServer)world, new GameProfile((UUID)null, "FakeThaumcraftGolem"));
/*  35 */     fp.field_71135_a = new FakeNetHandlerPlayServer(fp.field_71133_b, new NetworkManager(EnumPacketDirection.CLIENTBOUND), fp);
/*  36 */     fp.func_70080_a((golem.getGolemEntity()).field_70165_t, (golem.getGolemEntity()).field_70163_u, (golem.getGolemEntity()).field_70161_v, 
/*  37 */         (golem.getGolemEntity()).field_70177_z, (golem.getGolemEntity()).field_70125_A);
/*     */     
/*  39 */     IBlockState bs = world.func_180495_p(pos);
/*     */     
/*  41 */     fp.func_184611_a(EnumHand.MAIN_HAND, clickStack);
/*  42 */     fp.func_70095_a(sneaking);
/*     */     
/*  44 */     if (!rightClick) {
/*     */       
/*     */       try {
/*  47 */         fp.field_71134_c.func_180784_a(pos, face);
/*  48 */       } catch (Exception exception) {}
/*     */     }
/*     */     else {
/*     */       
/*  52 */       if (fp.func_184614_ca().func_77973_b() instanceof ItemBlock && 
/*  53 */         !mayPlace(world, ((ItemBlock)fp.func_184614_ca().func_77973_b()).func_179223_d(), pos, face)) {
/*  54 */         golem.getGolemEntity().func_70107_b(
/*  55 */             (golem.getGolemEntity()).field_70165_t + face.func_82601_c(), 
/*  56 */             (golem.getGolemEntity()).field_70163_u + face.func_96559_d(), 
/*  57 */             (golem.getGolemEntity()).field_70161_v + face.func_82599_e());
/*     */       }
/*     */       try {
/*  60 */         fp.field_71134_c.func_187251_a(fp, world, fp.func_184614_ca(), EnumHand.MAIN_HAND, pos, face, 0.5F, 0.5F, 0.5F);
/*     */       }
/*  62 */       catch (Exception exception) {}
/*     */     } 
/*     */     
/*  65 */     golem.addRankXp(1);
/*     */     
/*  67 */     if (!fp.func_184614_ca().func_190926_b() && fp.func_184614_ca().func_190916_E() <= 0) {
/*  68 */       fp.func_184611_a(EnumHand.MAIN_HAND, ItemStack.field_190927_a);
/*     */     }
/*  70 */     dropSomeItems(fp, golem);
/*     */     
/*  72 */     golem.swingArm();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean mayPlace(World world, Block blockIn, BlockPos pos, EnumFacing side) {
/*  78 */     IBlockState block = world.func_180495_p(pos);
/*  79 */     AxisAlignedBB axisalignedbb = blockIn.func_185496_a(blockIn.func_176223_P(), world, pos);
/*  80 */     if (axisalignedbb != null && !world.func_72917_a(axisalignedbb, null)) return false; 
/*  81 */     return true;
/*     */   }
/*     */   
/*     */   private static void dropSomeItems(FakePlayer fp2, IGolemAPI golem) {
/*     */     int i;
/*  86 */     for (i = 0; i < fp2.field_71071_by.field_70462_a.size(); i++) {
/*     */       
/*  88 */       if (!((ItemStack)fp2.field_71071_by.field_70462_a.get(i)).func_190926_b()) {
/*     */         
/*  90 */         if (golem.canCarry((ItemStack)fp2.field_71071_by.field_70462_a.get(i), true)) {
/*  91 */           fp2.field_71071_by.field_70462_a.set(i, golem.holdItem((ItemStack)fp2.field_71071_by.field_70462_a.get(i)));
/*     */         }
/*  93 */         if (!((ItemStack)fp2.field_71071_by.field_70462_a.get(i)).func_190926_b() && ((ItemStack)fp2.field_71071_by.field_70462_a.get(i)).func_190916_E() > 0)
/*  94 */           InventoryUtils.dropItemAtEntity(golem.getGolemWorld(), (ItemStack)fp2.field_71071_by.field_70462_a.get(i), golem.getGolemEntity()); 
/*  95 */         fp2.field_71071_by.field_70462_a.set(i, ItemStack.field_190927_a);
/*     */       } 
/*     */     } 
/*  98 */     for (i = 0; i < fp2.field_71071_by.field_70460_b.size(); i++) {
/*     */       
/* 100 */       if (!((ItemStack)fp2.field_71071_by.field_70460_b.get(i)).func_190926_b()) {
/*     */         
/* 102 */         if (golem.canCarry((ItemStack)fp2.field_71071_by.field_70460_b.get(i), true)) {
/* 103 */           fp2.field_71071_by.field_70460_b.set(i, golem.holdItem((ItemStack)fp2.field_71071_by.field_70460_b.get(i)));
/*     */         }
/* 105 */         if (!((ItemStack)fp2.field_71071_by.field_70462_a.get(i)).func_190926_b() && ((ItemStack)fp2.field_71071_by.field_70460_b.get(i)).func_190916_E() > 0)
/* 106 */           InventoryUtils.dropItemAtEntity(golem.getGolemWorld(), (ItemStack)fp2.field_71071_by.field_70460_b.get(i), golem.getGolemEntity()); 
/* 107 */         fp2.field_71071_by.field_70460_b.set(i, ItemStack.field_190927_a);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\GolemInteractionHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */