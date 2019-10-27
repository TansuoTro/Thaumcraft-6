/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityFurnace;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.TileThaumcraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileBellows
/*     */   extends TileThaumcraft
/*     */   implements ITickable
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  28 */     return new AxisAlignedBB(
/*  29 */         func_174877_v().func_177958_n() - 0.3D, func_174877_v().func_177956_o() - 0.3D, func_174877_v().func_177952_p() - 0.3D, 
/*  30 */         func_174877_v().func_177958_n() + 1.3D, func_174877_v().func_177956_o() + 1.3D, func_174877_v().func_177952_p() + 1.3D);
/*     */   }
/*     */   
/*  33 */   public float inflation = 1.0F;
/*     */   boolean direction = false;
/*     */   boolean firstrun = true;
/*  36 */   public int delay = 0;
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/*  40 */     if (this.field_145850_b.field_72995_K) {
/*  41 */       if (BlockStateUtils.isEnabled(func_145832_p())) {
/*  42 */         if (this.firstrun)
/*  43 */           this.inflation = 0.35F + this.field_145850_b.field_73012_v.nextFloat() * 0.55F; 
/*  44 */         this.firstrun = false;
/*     */         
/*  46 */         if (this.inflation > 0.35F && !this.direction) this.inflation -= 0.075F; 
/*  47 */         if (this.inflation <= 0.35F && !this.direction)
/*     */         {
/*  49 */           this.direction = true;
/*     */         }
/*     */         
/*  52 */         if (this.inflation < 1.0F && this.direction) this.inflation += 0.025F; 
/*  53 */         if (this.inflation >= 1.0F && this.direction) {
/*  54 */           this.direction = false;
/*  55 */           this.field_145850_b.func_184134_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), SoundEvents.field_187557_bK, SoundCategory.BLOCKS, 0.01F, 0.5F + (this.field_145850_b.field_73012_v
/*  56 */               .nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.2F, false);
/*     */         }
/*     */       
/*     */       } 
/*  60 */     } else if (BlockStateUtils.isEnabled(func_145832_p())) {
/*  61 */       this.delay++;
/*  62 */       if (this.delay >= 2) {
/*  63 */         this.delay = 0;
/*  64 */         TileEntity tile = this.field_145850_b.func_175625_s(this.field_174879_c.func_177972_a(BlockStateUtils.getFacing(func_145832_p())));
/*  65 */         if (tile != null && tile instanceof TileEntityFurnace) {
/*  66 */           TileEntityFurnace tf = (TileEntityFurnace)tile;
/*  67 */           int ct = getCooktime(tf);
/*  68 */           if (ct > 0 && ct < 199) setCooktime(tf, ct + 1);
/*     */         
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*  76 */   public void setCooktime(TileEntityFurnace ent, int hit) { ent.field_174906_k = hit; }
/*     */ 
/*     */ 
/*     */   
/*  80 */   public int getCooktime(TileEntityFurnace ent) { return ent.field_174906_k; }
/*     */ 
/*     */   
/*     */   public static int getBellows(World world, BlockPos pos, EnumFacing[] directions) {
/*  84 */     int bellows = 0;
/*  85 */     for (EnumFacing dir : directions) {
/*  86 */       TileEntity tile = world.func_175625_s(pos.func_177972_a(dir));
/*     */       try {
/*  88 */         if (tile != null && tile instanceof TileBellows && 
/*     */           
/*  90 */           BlockStateUtils.getFacing(tile.func_145832_p()) == dir
/*  91 */           .func_176734_d() && 
/*  92 */           BlockStateUtils.isEnabled(tile.func_145832_p())) {
/*  93 */           bellows++;
/*     */         }
/*  95 */       } catch (Exception exception) {}
/*     */     } 
/*  97 */     return bellows;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 102 */   public boolean canRenderBreaking() { return true; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileBellows.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */