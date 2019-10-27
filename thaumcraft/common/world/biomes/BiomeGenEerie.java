/*    */ package thaumcraft.common.world.biomes;
/*    */ 
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.config.ModConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BiomeGenEerie
/*    */   extends Biome
/*    */ {
/*    */   public BiomeGenEerie(Biome.BiomeProperties par1) {
/* 21 */     super(par1);
/* 22 */     setRegistryName("thaumcraft", "eerie");
/* 23 */     this.field_76762_K.clear();
/* 24 */     this.field_76762_K.add(new Biome.SpawnListEntry(net.minecraft.entity.passive.EntityBat.class, 3, 1, 1));
/*    */     
/* 26 */     this.field_76761_J.add(new Biome.SpawnListEntry(net.minecraft.entity.monster.EntityWitch.class, 8, 1, 1));
/* 27 */     this.field_76761_J.add(new Biome.SpawnListEntry(net.minecraft.entity.monster.EntityEnderman.class, 4, 1, 1));
/* 28 */     if (ModConfig.CONFIG_WORLD.allowSpawnAngryZombie) {
/* 29 */       this.field_76761_J.add(new Biome.SpawnListEntry(thaumcraft.common.entities.monster.EntityBrainyZombie.class, 32, 1, 1));
/* 30 */       this.field_76761_J.add(new Biome.SpawnListEntry(thaumcraft.common.entities.monster.EntityGiantBrainyZombie.class, 8, 1, 1));
/*    */     } 
/* 32 */     if (ModConfig.CONFIG_WORLD.allowSpawnWisp)
/* 33 */       this.field_76761_J.add(new Biome.SpawnListEntry(thaumcraft.common.entities.monster.EntityWisp.class, 3, 1, 1)); 
/* 34 */     if (ModConfig.CONFIG_WORLD.allowSpawnElder) {
/* 35 */       this.field_76761_J.add(new Biome.SpawnListEntry(thaumcraft.common.entities.monster.EntityEldritchGuardian.class, 1, 1, 1));
/*    */     }
/* 37 */     this.field_76760_I.field_76832_z = 2;
/* 38 */     this.field_76760_I.field_76802_A = 1;
/* 39 */     this.field_76760_I.field_76803_B = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/* 46 */   public int func_180627_b(BlockPos p_180627_1_) { return 4212800; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/* 53 */   public int func_180625_c(BlockPos p_180625_1_) { return 4212800; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 58 */   public int func_76731_a(float par1) { return 2237081; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 63 */   public int getWaterColorMultiplier() { return 3035999; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\world\biomes\BiomeGenEerie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */