/*     */ package thaumcraft.common.items.casters.foci;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.casters.FocusMedium;
/*     */ import thaumcraft.api.casters.NodeSetting;
/*     */ import thaumcraft.api.casters.Trajectory;
/*     */ import thaumcraft.api.items.IArchitect;
/*     */ import thaumcraft.common.items.casters.CasterManager;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FocusMediumPlan
/*     */   extends FocusMedium
/*     */   implements IArchitect
/*     */ {
/*  31 */   public String getResearch() { return "FOCUSPLAN"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   public String getKey() { return "thaumcraft.PLAN"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public int getComplexity() { return 4; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public Aspect getAspect() { return Aspect.CRAFT; }
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeSetting[] createSettings() {
/*  51 */     int[] method = { 0, 1 };
/*  52 */     String[] methodDesc = { "focus.plan.full", "focus.plan.surface" };
/*  53 */     return new NodeSetting[] { new NodeSetting("method", "focus.plan.method", new NodeSetting.NodeSettingIntList(method, methodDesc)) };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RayTraceResult[] supplyTargets() {
/*  60 */     if (getParent() == null || !(getPackage().getCaster() instanceof EntityPlayer)) return new RayTraceResult[0]; 
/*  61 */     ArrayList<RayTraceResult> targets = new ArrayList<RayTraceResult>();
/*     */     
/*  63 */     ItemStack casterStack = ItemStack.field_190927_a;
/*  64 */     if (getPackage().getCaster().func_184614_ca() != null && getPackage().getCaster().func_184614_ca().func_77973_b() instanceof thaumcraft.api.casters.ICaster) {
/*  65 */       casterStack = getPackage().getCaster().func_184614_ca();
/*     */     }
/*  67 */     else if (getPackage().getCaster().func_184592_cb() != null && getPackage().getCaster().func_184592_cb().func_77973_b() instanceof thaumcraft.api.casters.ICaster) {
/*  68 */       casterStack = getPackage().getCaster().func_184592_cb();
/*     */     } 
/*     */     
/*  71 */     for (Trajectory sT : getParent().supplyTrajectories()) {
/*  72 */       Vec3d end = sT.direction.func_72432_b();
/*  73 */       end = end.func_186678_a(16.0D);
/*  74 */       end = end.func_178787_e(sT.source);
/*  75 */       RayTraceResult target = (getPackage()).world.func_72933_a(sT.source, end);
/*  76 */       if (target != null && target.field_72313_a == RayTraceResult.Type.BLOCK) {
/*  77 */         ArrayList<BlockPos> usl = getArchitectBlocks(casterStack, (getPackage()).world, target.func_178782_a(), target.field_178784_b, (EntityPlayer)getPackage().getCaster());
/*  78 */         ArrayList<BlockPos> sl = (ArrayList)usl.stream().sorted(new BlockUtils.BlockPosComparator(target.func_178782_a())).collect(Collectors.toList());
/*     */         
/*  80 */         for (BlockPos p : sl) {
/*  81 */           targets.add(new RayTraceResult(new Vec3d(p.func_177958_n() + 0.5D, p.func_177956_o() + 0.5D, p.func_177952_p() + 0.5D), target.field_178784_b, p));
/*     */         }
/*     */       } 
/*     */     } 
/*  85 */     return (RayTraceResult[])targets.toArray(new RayTraceResult[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public RayTraceResult getArchitectMOP(ItemStack stack, World world, EntityLivingBase player) {
/*  90 */     Vec3d start = player.func_174791_d();
/*  91 */     start = start.func_72441_c(0.0D, player.func_70047_e(), 0.0D);
/*  92 */     Vec3d end = player.func_70040_Z();
/*  93 */     end = end.func_186678_a(16.0D);
/*  94 */     end = end.func_178787_e(start);
/*  95 */     return world.func_72933_a(start, end);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 100 */   public boolean useBlockHighlight(ItemStack stack) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public boolean isExclusive() { return true; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean showAxis(ItemStack stack, World world, EntityPlayer player, EnumFacing side, IArchitect.EnumAxis axis) {
/* 110 */     if (stack == null || stack.func_190926_b()) return false; 
/* 111 */     int dim = CasterManager.getAreaDim(stack);
/* 112 */     if (getSettingValue("method") == 0) {
/* 113 */       switch (axis) {
/*     */         case Y:
/* 115 */           if (dim == 0 || dim == 3) return true; 
/*     */           break;
/*     */         case Z:
/* 118 */           if (dim == 0 || dim == 2) return true; 
/*     */           break;
/*     */         case X:
/* 121 */           if (dim == 0 || dim == 1) return true; 
/*     */           break;
/*     */       } 
/*     */     } else {
/* 125 */       switch (side.func_176740_k()) {
/*     */         case Y:
/* 127 */           if ((axis == IArchitect.EnumAxis.X && (dim == 0 || dim == 1)) || (axis == IArchitect.EnumAxis.Z && (dim == 0 || dim == 2))) return true; 
/*     */           break;
/*     */         case Z:
/* 130 */           if ((axis == IArchitect.EnumAxis.Y && (dim == 0 || dim == 1)) || (axis == IArchitect.EnumAxis.X && (dim == 0 || dim == 2))) return true; 
/*     */           break;
/*     */         case X:
/* 133 */           if ((axis == IArchitect.EnumAxis.Y && (dim == 0 || dim == 1)) || (axis == IArchitect.EnumAxis.Z && (dim == 0 || dim == 2))) return true; 
/*     */           break;
/*     */       } 
/*     */     } 
/* 137 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<BlockPos> getArchitectBlocks(ItemStack stack, World world, BlockPos pos, EnumFacing side, EntityPlayer player) {
/* 143 */     ArrayList<BlockPos> out = new ArrayList<BlockPos>();
/* 144 */     if (stack == null || stack.func_190926_b()) return out; 
/* 145 */     if (getSettingValue("method") == 0) {
/* 146 */       this.checked.clear();
/* 147 */       checkNeighboursFull(world, pos, new BlockPos(pos), side, 
/* 148 */           CasterManager.getAreaX(stack), 
/* 149 */           CasterManager.getAreaY(stack), 
/* 150 */           CasterManager.getAreaZ(stack), out, player);
/*     */     } else {
/* 152 */       IBlockState bi = world.func_180495_p(pos);
/* 153 */       this.checked.clear();
/* 154 */       if (side.func_176740_k() == EnumFacing.Axis.Z) {
/* 155 */         checkNeighboursSurface(world, pos, bi, new BlockPos(pos), side, 
/* 156 */             CasterManager.getAreaZ(stack), 
/* 157 */             CasterManager.getAreaY(stack), 
/* 158 */             CasterManager.getAreaX(stack), out, player);
/*     */       } else {
/* 160 */         checkNeighboursSurface(world, pos, bi, new BlockPos(pos), side, 
/* 161 */             CasterManager.getAreaX(stack), 
/* 162 */             CasterManager.getAreaY(stack), 
/* 163 */             CasterManager.getAreaZ(stack), out, player);
/*     */       } 
/* 165 */     }  return out;
/*     */   }
/*     */   
/* 168 */   ArrayList<BlockPos> checked = new ArrayList();
/*     */ 
/*     */   
/*     */   public void checkNeighboursFull(World world, BlockPos pos1, BlockPos pos2, EnumFacing side, int sizeX, int sizeY, int sizeZ, ArrayList<BlockPos> list, EntityPlayer player) {
/* 172 */     if (this.checked.contains(pos2))
/* 173 */       return;  this.checked.add(pos2);
/*     */     
/* 175 */     if (!world.func_175623_d(pos2))
/*     */     {
/*     */       
/* 178 */       list.add(pos2);
/*     */     }
/*     */     
/* 181 */     int xs = -sizeX + pos1.func_177958_n();
/* 182 */     int xe = sizeX + pos1.func_177958_n();
/* 183 */     int ys = -sizeY + pos1.func_177956_o();
/* 184 */     int ye = sizeY + pos1.func_177956_o();
/* 185 */     int zs = -sizeZ + pos1.func_177952_p();
/* 186 */     int ze = sizeZ + pos1.func_177952_p();
/* 187 */     xs -= sizeX * side.func_82601_c();
/* 188 */     xe -= sizeX * side.func_82601_c();
/* 189 */     ys -= sizeY * side.func_96559_d();
/* 190 */     ye -= sizeY * side.func_96559_d();
/* 191 */     zs -= sizeZ * side.func_82599_e();
/* 192 */     ze -= sizeZ * side.func_82599_e();
/*     */     
/* 194 */     for (EnumFacing dir : EnumFacing.values()) {
/* 195 */       BlockPos q = pos2.func_177972_a(dir);
/* 196 */       if (q.func_177958_n() >= xs && q.func_177958_n() <= xe && q.func_177956_o() >= ys && q.func_177956_o() <= ye && q.func_177952_p() >= zs && q.func_177952_p() <= ze) {
/* 197 */         checkNeighboursFull(world, pos1, q, side, sizeX, sizeY, sizeZ, list, player);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void checkNeighboursSurface(World world, BlockPos pos1, IBlockState bi, BlockPos pos2, EnumFacing side, int sizeX, int sizeY, int sizeZ, ArrayList<BlockPos> list, EntityPlayer player) {
/* 203 */     if (this.checked.contains(pos2))
/* 204 */       return;  this.checked.add(pos2);
/* 205 */     switch (side.func_176740_k()) {
/*     */       case Y:
/* 207 */         if (Math.abs(pos2.func_177958_n() - pos1.func_177958_n()) > sizeX)
/* 208 */           return;  if (Math.abs(pos2.func_177952_p() - pos1.func_177952_p()) > sizeZ)
/*     */           return;  break;
/*     */       case Z:
/* 211 */         if (Math.abs(pos2.func_177958_n() - pos1.func_177958_n()) > sizeX)
/* 212 */           return;  if (Math.abs(pos2.func_177956_o() - pos1.func_177956_o()) > sizeZ)
/*     */           return;  break;
/*     */       case X:
/* 215 */         if (Math.abs(pos2.func_177956_o() - pos1.func_177956_o()) > sizeX)
/* 216 */           return;  if (Math.abs(pos2.func_177952_p() - pos1.func_177952_p()) > sizeZ)
/*     */           return; 
/*     */         break;
/*     */     } 
/* 220 */     if (world.func_180495_p(pos2) == bi && 
/* 221 */       BlockUtils.isBlockExposed(world, pos2) && !world.func_175623_d(pos2)) {
/*     */ 
/*     */       
/* 224 */       list.add(pos2);
/*     */     } else {
/*     */       return;
/*     */     } 
/* 228 */     for (EnumFacing dir : EnumFacing.values()) {
/* 229 */       if (dir != side && dir.func_176734_d() != side)
/* 230 */         checkNeighboursSurface(world, pos1, bi, pos2.func_177972_a(dir), side, sizeX, sizeY, sizeZ, list, player); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\foci\FocusMediumPlan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */