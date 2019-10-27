/*    */ package thaumcraft.common.entities.construct;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ import net.minecraft.util.EnumActionResult;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.items.ItemTCBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemTurretPlacer
/*    */   extends ItemTCBase
/*    */ {
/* 21 */   public ItemTurretPlacer() { super("turret", new String[] { "basic", "advanced", "bore" }); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
/* 29 */     if (side == EnumFacing.DOWN)
/*    */     {
/* 31 */       return EnumActionResult.PASS;
/*    */     }
/*    */ 
/*    */     
/* 35 */     boolean flag = world.func_180495_p(pos).func_177230_c().func_176200_f(world, pos);
/* 36 */     BlockPos blockpos = flag ? pos : pos.func_177972_a(side);
/*    */     
/* 38 */     if (!player.func_175151_a(blockpos, side, player.func_184586_b(hand)))
/*    */     {
/* 40 */       return EnumActionResult.PASS;
/*    */     }
/*    */ 
/*    */     
/* 44 */     BlockPos blockpos1 = blockpos.func_177984_a();
/* 45 */     boolean flag1 = (!world.func_175623_d(blockpos) && !world.func_180495_p(blockpos).func_177230_c().func_176200_f(world, blockpos));
/* 46 */     flag1 |= ((!world.func_175623_d(blockpos1) && !world.func_180495_p(blockpos1).func_177230_c().func_176200_f(world, blockpos1)));
/*    */     
/* 48 */     if (flag1)
/*    */     {
/* 50 */       return EnumActionResult.PASS;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 55 */     double d0 = blockpos.func_177958_n();
/* 56 */     double d1 = blockpos.func_177956_o();
/* 57 */     double d2 = blockpos.func_177952_p();
/* 58 */     List<Entity> list = world.func_72839_b((Entity)null, new AxisAlignedBB(d0, d1, d2, d0 + 1.0D, d1 + 2.0D, d2 + 1.0D));
/*    */     
/* 60 */     if (!list.isEmpty())
/*    */     {
/* 62 */       return EnumActionResult.PASS;
/*    */     }
/*    */ 
/*    */     
/* 66 */     if (!world.field_72995_K) {
/*    */       
/* 68 */       world.func_175698_g(blockpos);
/* 69 */       world.func_175698_g(blockpos1);
/*    */       
/* 71 */       EntityOwnedConstruct turret = null;
/* 72 */       switch (player.func_184586_b(hand).func_77952_i()) { case 0:
/* 73 */           turret = new EntityTurretCrossbow(world, blockpos); break;
/* 74 */         case 1: turret = new EntityTurretCrossbowAdvanced(world, blockpos); break;
/* 75 */         case 2: turret = new EntityArcaneBore(world, blockpos, player.func_174811_aO());
/*    */           break; }
/*    */ 
/*    */       
/* 79 */       if (turret != null) {
/* 80 */         world.func_72838_d(turret);
/* 81 */         turret.setOwned(true);
/* 82 */         turret.setValidSpawn();
/* 83 */         turret.setOwnerId(player.func_110124_au());
/* 84 */         world.func_184148_a((EntityPlayer)null, turret.field_70165_t, turret.field_70163_u, turret.field_70161_v, SoundEvents.field_187710_m, SoundCategory.BLOCKS, 0.75F, 0.8F);
/*    */       } 
/* 86 */       player.func_184586_b(hand).func_190918_g(1);
/* 87 */       return EnumActionResult.SUCCESS;
/*    */     } 
/* 89 */     return EnumActionResult.PASS;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\construct\ItemTurretPlacer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */