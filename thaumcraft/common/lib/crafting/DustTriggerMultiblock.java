/*     */ package thaumcraft.common.lib.crafting;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.crafting.IDustTrigger;
/*     */ import thaumcraft.api.crafting.Part;
/*     */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*     */ import thaumcraft.common.container.InventoryFake;
/*     */ import thaumcraft.common.lib.events.ServerEvents;
/*     */ import thaumcraft.common.lib.events.ToolEvents;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ 
/*     */ public class DustTriggerMultiblock implements IDustTrigger {
/*     */   Part[][][] blueprint;
/*     */   String research;
/*     */   int ySize;
/*     */   int xSize;
/*     */   int zSize;
/*     */   
/*     */   public DustTriggerMultiblock(String research, Part[][][] blueprint) {
/*  32 */     this.blueprint = blueprint;
/*  33 */     this.research = research;
/*  34 */     this.ySize = this.blueprint.length;
/*  35 */     this.xSize = this.blueprint[0].length;
/*  36 */     this.zSize = this.blueprint[0][0].length;
/*     */   }
/*     */ 
/*     */   
/*     */   public IDustTrigger.Placement getValidFace(World world, EntityPlayer player, BlockPos pos, EnumFacing face) {
/*  41 */     if (this.research != null && !ThaumcraftCapabilities.getKnowledge(player).isResearchKnown(this.research))
/*  42 */       return null; 
/*  43 */     for (int yy = -this.ySize; yy <= 0; yy++) {
/*  44 */       for (int xx = -this.xSize; xx <= 0; xx++) {
/*  45 */         for (int zz = -this.zSize; zz <= 0; zz++) {
/*  46 */           BlockPos p2 = pos.func_177982_a(xx, yy, zz);
/*  47 */           EnumFacing f = fitMultiblock(world, p2);
/*  48 */           if (f != null)
/*  49 */             return new IDustTrigger.Placement(xx, yy, zz, f); 
/*     */         } 
/*     */       } 
/*     */     } 
/*  53 */     return null;
/*     */   }
/*     */   private EnumFacing fitMultiblock(World world, BlockPos pos) {
/*     */     EnumFacing[] arrayOfEnumFacing;
/*     */     int i;
/*     */     byte b;
/*  59 */     label41: for (arrayOfEnumFacing = EnumFacing.field_176754_o, i = arrayOfEnumFacing.length, b = 0; b < i; ) { EnumFacing face = arrayOfEnumFacing[b];
/*  60 */       for (int y = 0; y < this.ySize; y++) {
/*  61 */         Matrix matrix = new Matrix(this.blueprint[y]);
/*  62 */         matrix.Rotate90DegRight(3 - face.func_176736_b());
/*  63 */         for (int x = 0; x < matrix.rows; x++) {
/*  64 */           for (int z = 0; z < matrix.cols; z++) {
/*  65 */             if (matrix.matrix[x][z] != null) {
/*  66 */               IBlockState bsWo = world.func_180495_p(pos.func_177982_a(x, -y + this.ySize - 1, z));
/*  67 */               if (matrix.matrix[x][z].getSource() instanceof Block && bsWo
/*  68 */                 .func_177230_c() != (Block)matrix.matrix[x][z].getSource()) {
/*     */                 continue label41;
/*     */               }
/*  71 */               if (matrix.matrix[x][z].getSource() instanceof Material && bsWo
/*  72 */                 .func_185904_a() != (Material)matrix.matrix[x][z].getSource()) {
/*     */                 continue label41;
/*     */               }
/*  75 */               if (matrix.matrix[x][z].getSource() instanceof ItemStack) if (bsWo
/*  76 */                   .func_177230_c() == Block.func_149634_a(((ItemStack)matrix.matrix[x][z].getSource()).func_77973_b())) { if (bsWo
/*  77 */                     .func_177230_c().func_176201_c(bsWo) != ((ItemStack)matrix.matrix[x][z].getSource()).func_77952_i())
/*     */                     continue label41;  }
/*     */                 else { continue label41; }
/*  80 */                   if (matrix.matrix[x][z].getSource() instanceof IBlockState && bsWo != matrix.matrix[x][z].getSource()) {
/*     */                 b++; continue;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*  86 */       }  return face; }
/*     */     
/*  88 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<BlockPos> sparkle(World world, EntityPlayer player, BlockPos pos, IDustTrigger.Placement placement) {
/*  94 */     BlockPos p2 = pos.func_177982_a(placement.xOffset, placement.yOffset, placement.zOffset);
/*  95 */     ArrayList<BlockPos> list = new ArrayList<BlockPos>();
/*     */     
/*  97 */     for (int y = 0; y < this.ySize; y++) {
/*  98 */       Matrix matrix = new Matrix(this.blueprint[y]);
/*  99 */       matrix.Rotate90DegRight(3 - placement.facing.func_176736_b());
/* 100 */       for (int x = 0; x < matrix.rows; x++) {
/* 101 */         for (int z = 0; z < matrix.cols; z++) {
/* 102 */           if (matrix.matrix[x][z] != null) {
/* 103 */             BlockPos p3 = p2.func_177982_a(x, -y + this.ySize - 1, z);
/* 104 */             if (matrix.matrix[x][z].getSource() != null && BlockUtils.isBlockExposed(world, p3))
/* 105 */               list.add(p3); 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 110 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(final World world, final EntityPlayer player, BlockPos pos, IDustTrigger.Placement placement, EnumFacing side) {
/* 115 */     if (!world.field_72995_K) {
/*     */       
/* 117 */       FMLCommonHandler.instance().firePlayerCraftingEvent(player, new ItemStack(BlocksTC.infernalFurnace), new InventoryFake(1));
/*     */       
/* 119 */       BlockPos p2 = pos.func_177982_a(placement.xOffset, placement.yOffset, placement.zOffset);
/*     */       
/* 121 */       for (int y = 0; y < this.ySize; y++) {
/* 122 */         Matrix matrix = new Matrix(this.blueprint[y]);
/* 123 */         matrix.Rotate90DegRight(3 - placement.facing.func_176736_b());
/* 124 */         for (int x = 0; x < matrix.rows; x++) {
/* 125 */           for (int z = 0; z < matrix.cols; z++) {
/*     */             
/* 127 */             if (matrix.matrix[x][z] != null && matrix.matrix[x][z].getTarget() != null) {
/*     */               final Object sourceObject;
/*     */               final ItemStack targetObject;
/* 130 */               if (matrix.matrix[x][z].getTarget() instanceof Block) {
/* 131 */                 int meta = 0;
/* 132 */                 sourceObject = side;
/* 133 */                 if ((Block)matrix.matrix[x][z].getTarget() instanceof IBlockFacingHorizontal) {
/* 134 */                   if (sourceObject.func_176736_b() < 0) {
/* 135 */                     sourceObject = player.func_174811_aO().func_176734_d();
/*     */                   }
/*     */                   
/* 138 */                   IBlockState state = ((Block)matrix.matrix[x][z].getTarget()).func_176223_P().func_177226_a(IBlockFacingHorizontal.FACING, 
/* 139 */                       matrix.matrix[x][z].getApplyPlayerFacing() ? sourceObject : (
/* 140 */                       matrix.matrix[x][z].isOpp() ? placement.facing.func_176734_d() : placement.facing));
/* 141 */                   meta = ((Block)matrix.matrix[x][z].getTarget()).func_176201_c(state);
/*     */                 } 
/* 143 */                 targetObject = new ItemStack((Block)matrix.matrix[x][z].getTarget(), 1, meta);
/*     */               }
/* 145 */               else if (matrix.matrix[x][z].getTarget() instanceof ItemStack) {
/* 146 */                 targetObject = ((ItemStack)matrix.matrix[x][z].getTarget()).func_77946_l();
/*     */               } else {
/* 148 */                 targetObject = null;
/*     */               } 
/*     */               
/* 151 */               final BlockPos p3 = p2.func_177982_a(x, -y + this.ySize - 1, z);
/*     */ 
/*     */               
/* 154 */               if (matrix.matrix[x][z].getSource() instanceof Block) {
/* 155 */                 sourceObject = world.func_180495_p(p3);
/*     */               }
/* 157 */               else if (matrix.matrix[x][z].getSource() instanceof Material) {
/* 158 */                 sourceObject = (Material)matrix.matrix[x][z].getSource();
/*     */               }
/* 160 */               else if (matrix.matrix[x][z].getSource() instanceof IBlockState) {
/* 161 */                 sourceObject = (IBlockState)matrix.matrix[x][z].getSource();
/*     */               } else {
/* 163 */                 sourceObject = null;
/*     */               } 
/*     */               
/* 166 */               ToolEvents.addBlockedBlock(world, p3);
/*     */               
/* 168 */               ServerEvents.addRunnableServer(world, new Runnable() {
/* 169 */                     public void run() { ServerEvents.addSwapper(world, p3, sourceObject, targetObject, false, 0, player, true, false, -9999, false, false, 0, ServerEvents.DEFAULT_PREDICATE, 0.0F);
/*     */                       
/* 171 */                       ToolEvents.clearBlockedBlock(world, p3); }
/* 172 */                   }matrix.matrix[x][z].getPriority());
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\crafting\DustTriggerMultiblock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */