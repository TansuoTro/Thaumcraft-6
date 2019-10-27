/*    */ package thaumcraft.common.blocks.world.ore;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ 
/*    */ public static enum ShardType
/*    */ {
/*  8 */   AIR(0, "air", Aspect.AIR),
/*  9 */   FIRE(1, "fire", Aspect.FIRE),
/* 10 */   WATER(2, "water", Aspect.WATER),
/* 11 */   EARTH(3, "earth", Aspect.EARTH),
/* 12 */   ORDER(4, "order", Aspect.ORDER),
/* 13 */   ENTROPY(5, "entropy", Aspect.ENTROPY),
/* 14 */   FLUX(6, "flux", Aspect.FLUX);
/*    */   static  {
/* 16 */     METADATA_LOOKUP = new ShardType[values().length];
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 89 */     var0 = values();
/* 90 */     int var1 = var0.length;
/*    */     
/* 92 */     for (int var2 = 0; var2 < var1; var2++) {
/*    */       
/* 94 */       ShardType var3 = var0[var2];
/* 95 */       METADATA_LOOKUP[var3.getMetadata()] = var3;
/*    */     } 
/*    */   }
/*    */   
/*    */   private static final ShardType[] METADATA_LOOKUP;
/*    */   private final int metadata;
/*    */   private final String name;
/*    */   private final Aspect aspect;
/*    */   private Block ore;
/*    */   
/*    */   ShardType(int metadata, String unlocalizedName, Aspect aspect) {
/*    */     this.metadata = metadata;
/*    */     this.name = unlocalizedName;
/*    */     this.aspect = aspect;
/*    */   }
/*    */   
/*    */   public int getMetadata() { return this.metadata; }
/*    */   
/*    */   public Aspect getAspect() { return this.aspect; }
/*    */   
/*    */   public Block getOre() { return this.ore; }
/*    */   
/*    */   public void setOre(Block b) { this.ore = b; }
/*    */   
/*    */   public String getUnlocalizedName() { return this.name; }
/*    */   
/*    */   public String toString() { return getUnlocalizedName(); }
/*    */   
/*    */   public static int getMetaByAspect(Aspect a) {
/*    */     ShardType[] var0 = values();
/*    */     int var1 = var0.length;
/*    */     for (int var2 = 0; var2 < var1; var2++) {
/*    */       if (var0[var2].getAspect() == a)
/*    */         return var2; 
/*    */     } 
/*    */     return -1;
/*    */   }
/*    */   
/*    */   public static ShardType byMetadata(int metadata) {
/*    */     if (metadata < 0 || metadata >= METADATA_LOOKUP.length)
/*    */       metadata = 0; 
/*    */     return METADATA_LOOKUP[metadata];
/*    */   }
/*    */   
/*    */   public String getName() { return this.name; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\world\ore\ShardType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */