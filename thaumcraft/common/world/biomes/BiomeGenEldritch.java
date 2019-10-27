/*    */ package thaumcraft.common.world.biomes;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BiomeGenEldritch
/*    */   extends Biome
/*    */ {
/*    */   public BiomeGenEldritch(Biome.BiomeProperties p_i1990_1_) {
/* 20 */     super(p_i1990_1_);
/* 21 */     setRegistryName("thaumcraft", "eldritch");
/* 22 */     this.field_76761_J.clear();
/* 23 */     this.field_76762_K.clear();
/* 24 */     this.field_76755_L.clear();
/* 25 */     this.field_82914_M.clear();
/* 26 */     this.field_76761_J.add(new Biome.SpawnListEntry(thaumcraft.common.entities.monster.EntityInhabitedZombie.class, 1, 1, 1));
/* 27 */     this.field_76761_J.add(new Biome.SpawnListEntry(thaumcraft.common.entities.monster.EntityEldritchGuardian.class, 1, 1, 1));
/* 28 */     this.field_76752_A = Blocks.field_150346_d.func_176223_P();
/* 29 */     this.field_76753_B = Blocks.field_150346_d.func_176223_P();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/* 38 */   public int func_76731_a(float p_76731_1_) { return 0; }
/*    */   
/*    */   public void func_180624_a(World world, Random random, BlockPos pos) {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\world\biomes\BiomeGenEldritch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */