/*     */ package thaumcraft.client.renderers.block;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.renderer.block.model.IBakedModel;
/*     */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*     */ import net.minecraft.client.renderer.block.model.ItemOverrideList;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.common.property.IExtendedBlockState;
/*     */ import thaumcraft.client.lib.obj.MeshLoader;
/*     */ import thaumcraft.client.lib.obj.MeshModel;
/*     */ import thaumcraft.client.lib.obj.MeshPart;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ import thaumcraft.common.blocks.world.ore.BlockCrystal;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CrystalModel
/*     */   implements IBakedModel
/*     */ {
/*     */   ResourceLocation model;
/*     */   MeshModel sourceMesh;
/*     */   TextureAtlasSprite tex;
/*     */   
/*     */   public CrystalModel(TextureAtlasSprite tex2) {
/*  35 */     this.model = new ResourceLocation("thaumcraft", "models/obj/crystal.obj");
/*  36 */     this.tex = tex2;
/*     */     try {
/*  38 */       this.sourceMesh = (new MeshLoader()).loadFromResource(this.model);
/*  39 */       for (MeshPart mp : this.sourceMesh.parts) {
/*  40 */         mp.tintIndex = 0;
/*     */       }
/*  42 */     } catch (IOException e) {
/*  43 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<BakedQuad> func_188616_a(IBlockState state, EnumFacing side, long rand) {
/*  52 */     if (side == null && state instanceof IExtendedBlockState) {
/*  53 */       List<BakedQuad> ret = new ArrayList<BakedQuad>();
/*     */       
/*  55 */       IExtendedBlockState es = (IExtendedBlockState)state;
/*  56 */       int m = ((BlockCrystal)state.func_177230_c()).getGrowth(state) + 1;
/*     */       
/*  58 */       MeshModel mm = this.sourceMesh.clone();
/*     */ 
/*     */       
/*     */       try {
/*  62 */         if (es != null && (!((Boolean)es.getValue(BlockCrystal.UP)).booleanValue() || !((Boolean)es.getValue(BlockCrystal.DOWN)).booleanValue() || 
/*  63 */           !((Boolean)es.getValue(BlockCrystal.EAST)).booleanValue() || !((Boolean)es.getValue(BlockCrystal.WEST)).booleanValue() || 
/*  64 */           !((Boolean)es.getValue(BlockCrystal.NORTH)).booleanValue() || !((Boolean)es.getValue(BlockCrystal.SOUTH)).booleanValue())) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  69 */           if (((Boolean)es.getValue(BlockCrystal.UP)).booleanValue()) {
/*  70 */             List<Integer> c = Arrays.asList(new Integer[] { null, null, null, null, null, null, null, (new Integer[8][6] = (new Integer[8][5] = (new Integer[8][4] = (new Integer[8][3] = (new Integer[8][2] = (new Integer[8][1] = (new Integer[8][0] = Integer.valueOf(0)).valueOf(1)).valueOf(2)).valueOf(3)).valueOf(4)).valueOf(5)).valueOf(6)).valueOf(7) });
/*  71 */             Collections.shuffle(c, new Random(rand));
/*  72 */             mm.parts.clear();
/*  73 */             for (int a = 0; a < m; ) { mm.parts.add(this.sourceMesh.parts.get(((Integer)c.get(a)).intValue())); a++; }
/*  74 */              MeshModel mod = mm.clone();
/*  75 */             mod.rotate(Math.toRadians(180.0D), new Vector3(1.0D, 0.0D, 0.0D), new Vector3(0.0D, 1.0D, 1.0D));
/*  76 */             for (BakedQuad quad : mod.bakeModel(func_177554_e())) {
/*  77 */               ret.add(quad);
/*     */             }
/*     */           } 
/*     */           
/*  81 */           if (((Boolean)es.getValue(BlockCrystal.DOWN)).booleanValue()) {
/*  82 */             List<Integer> c = Arrays.asList(new Integer[] { null, null, null, null, null, null, null, (new Integer[8][6] = (new Integer[8][5] = (new Integer[8][4] = (new Integer[8][3] = (new Integer[8][2] = (new Integer[8][1] = (new Integer[8][0] = Integer.valueOf(0)).valueOf(1)).valueOf(2)).valueOf(3)).valueOf(4)).valueOf(5)).valueOf(6)).valueOf(7) });
/*  83 */             Collections.shuffle(c, new Random(rand + 5L));
/*  84 */             mm.parts.clear();
/*  85 */             for (int a = 0; a < m; ) { mm.parts.add(this.sourceMesh.parts.get(((Integer)c.get(a)).intValue())); a++; }
/*  86 */              for (BakedQuad quad : mm.bakeModel(func_177554_e())) {
/*  87 */               ret.add(quad);
/*     */             }
/*     */           } 
/*     */           
/*  91 */           if (((Boolean)es.getValue(BlockCrystal.EAST)).booleanValue()) {
/*  92 */             List<Integer> c = Arrays.asList(new Integer[] { null, null, null, null, null, null, null, (new Integer[8][6] = (new Integer[8][5] = (new Integer[8][4] = (new Integer[8][3] = (new Integer[8][2] = (new Integer[8][1] = (new Integer[8][0] = Integer.valueOf(0)).valueOf(1)).valueOf(2)).valueOf(3)).valueOf(4)).valueOf(5)).valueOf(6)).valueOf(7) });
/*  93 */             Collections.shuffle(c, new Random(rand + 10L));
/*  94 */             mm.parts.clear();
/*  95 */             for (int a = 0; a < m; ) { mm.parts.add(this.sourceMesh.parts.get(((Integer)c.get(a)).intValue())); a++; }
/*  96 */              MeshModel mod = mm.clone();
/*  97 */             mod.rotate(Math.toRadians(90.0D), new Vector3(1.0D, 0.0D, 0.0D), new Vector3(0.0D, 0.0D, 0.0D));
/*  98 */             mod.rotate(Math.toRadians(270.0D), new Vector3(0.0D, 1.0D, 0.0D), new Vector3(1.0D, 1.0D, 0.0D));
/*  99 */             for (BakedQuad quad : mod.bakeModel(func_177554_e())) {
/* 100 */               ret.add(quad);
/*     */             }
/*     */           } 
/*     */           
/* 104 */           if (((Boolean)es.getValue(BlockCrystal.WEST)).booleanValue()) {
/* 105 */             List<Integer> c = Arrays.asList(new Integer[] { null, null, null, null, null, null, null, (new Integer[8][6] = (new Integer[8][5] = (new Integer[8][4] = (new Integer[8][3] = (new Integer[8][2] = (new Integer[8][1] = (new Integer[8][0] = Integer.valueOf(0)).valueOf(1)).valueOf(2)).valueOf(3)).valueOf(4)).valueOf(5)).valueOf(6)).valueOf(7) });
/* 106 */             Collections.shuffle(c, new Random(rand + 15L));
/* 107 */             mm.parts.clear();
/* 108 */             for (int a = 0; a < m; ) { mm.parts.add(this.sourceMesh.parts.get(((Integer)c.get(a)).intValue())); a++; }
/* 109 */              MeshModel mod = mm.clone();
/* 110 */             mod.rotate(Math.toRadians(90.0D), new Vector3(1.0D, 0.0D, 0.0D), new Vector3(0.0D, 0.0D, 0.0D));
/* 111 */             mod.rotate(Math.toRadians(90.0D), new Vector3(0.0D, 1.0D, 0.0D), new Vector3(0.0D, 1.0D, 1.0D));
/* 112 */             for (BakedQuad quad : mod.bakeModel(func_177554_e())) {
/* 113 */               ret.add(quad);
/*     */             }
/*     */           } 
/*     */           
/* 117 */           if (((Boolean)es.getValue(BlockCrystal.NORTH)).booleanValue()) {
/* 118 */             List<Integer> c = Arrays.asList(new Integer[] { null, null, null, null, null, null, null, (new Integer[8][6] = (new Integer[8][5] = (new Integer[8][4] = (new Integer[8][3] = (new Integer[8][2] = (new Integer[8][1] = (new Integer[8][0] = Integer.valueOf(0)).valueOf(1)).valueOf(2)).valueOf(3)).valueOf(4)).valueOf(5)).valueOf(6)).valueOf(7) });
/* 119 */             Collections.shuffle(c, new Random(rand + 20L));
/* 120 */             mm.parts.clear();
/* 121 */             for (int a = 0; a < m; ) { mm.parts.add(this.sourceMesh.parts.get(((Integer)c.get(a)).intValue())); a++; }
/* 122 */              MeshModel mod = mm.clone();
/* 123 */             mod.rotate(Math.toRadians(90.0D), new Vector3(1.0D, 0.0D, 0.0D), new Vector3(0.0D, 1.0D, 0.0D));
/* 124 */             for (BakedQuad quad : mod.bakeModel(func_177554_e())) {
/* 125 */               ret.add(quad);
/*     */             }
/*     */           } 
/*     */           
/* 129 */           if (((Boolean)es.getValue(BlockCrystal.SOUTH)).booleanValue()) {
/* 130 */             List<Integer> c = Arrays.asList(new Integer[] { null, null, null, null, null, null, null, (new Integer[8][6] = (new Integer[8][5] = (new Integer[8][4] = (new Integer[8][3] = (new Integer[8][2] = (new Integer[8][1] = (new Integer[8][0] = Integer.valueOf(0)).valueOf(1)).valueOf(2)).valueOf(3)).valueOf(4)).valueOf(5)).valueOf(6)).valueOf(7) });
/* 131 */             Collections.shuffle(c, new Random(rand + 25L));
/* 132 */             mm.parts.clear();
/* 133 */             for (int a = 0; a < m; ) { mm.parts.add(this.sourceMesh.parts.get(((Integer)c.get(a)).intValue())); a++; }
/* 134 */              MeshModel mod = mm.clone();
/* 135 */             mod.rotate(Math.toRadians(90.0D), new Vector3(1.0D, 0.0D, 0.0D), new Vector3(0.0D, 0.0D, 0.0D));
/* 136 */             mod.rotate(Math.toRadians(180.0D), new Vector3(0.0D, 1.0D, 0.0D), new Vector3(1.0D, 1.0D, 1.0D));
/* 137 */             for (BakedQuad quad : mod.bakeModel(func_177554_e())) {
/* 138 */               ret.add(quad);
/*     */             }
/*     */           } 
/*     */         } 
/* 142 */       } catch (Exception exception) {}
/*     */       
/* 144 */       return ret;
/*     */     } 
/*     */ 
/*     */     
/* 148 */     return ImmutableList.of();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public boolean func_177555_b() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   public boolean func_177556_c() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public boolean func_188618_c() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 169 */   public TextureAtlasSprite func_177554_e() { return this.tex; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   public ItemCameraTransforms func_177552_f() { return ItemCameraTransforms.field_178357_a; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 179 */   public ItemOverrideList func_188617_f() { return ItemOverrideList.field_188022_a; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\block\CrystalModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */