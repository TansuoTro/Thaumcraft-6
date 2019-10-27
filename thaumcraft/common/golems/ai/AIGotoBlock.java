/*     */ package thaumcraft.common.golems.ai;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.pathfinding.Path;
/*     */ import net.minecraft.pathfinding.PathPoint;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.golems.EntityThaumcraftGolem;
/*     */ import thaumcraft.common.golems.tasks.TaskHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AIGotoBlock
/*     */   extends AIGoto
/*     */ {
/*  20 */   public AIGotoBlock(EntityThaumcraftGolem g) { super(g, (byte)0); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/*  25 */     super.func_75246_d();
/*  26 */     if (this.golem.func_70671_ap() != null) {
/*  27 */       this.golem.func_70671_ap().func_75650_a(this.golem
/*  28 */           .getTask().getPos().func_177958_n() + 0.5D, this.golem.getTask().getPos().func_177956_o() + 0.5D, this.golem.getTask().getPos().func_177952_p() + 0.5D, 10.0F, this.golem
/*  29 */           .func_70646_bf());
/*     */     }
/*     */   }
/*     */   
/*     */   protected void moveTo() {
/*  34 */     if (this.targetBlock != null) {
/*  35 */       this.golem.func_70661_as().func_75492_a(this.targetBlock.func_177958_n() + 0.5D, this.targetBlock.func_177956_o() + 0.5D, this.targetBlock.func_177952_p() + 0.5D, this.golem.getGolemMoveSpeed());
/*     */     } else {
/*  37 */       this.golem.func_70661_as().func_75492_a(this.golem
/*  38 */           .getTask().getPos().func_177958_n() + 0.5D, this.golem.getTask().getPos().func_177956_o() + 0.5D, this.golem.getTask().getPos().func_177952_p() + 0.5D, this.golem.getGolemMoveSpeed());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean findDestination() {
/*  44 */     ArrayList<Task> list = TaskHandler.getBlockTasksSorted(this.golem.field_70170_p.field_73011_w
/*  45 */         .getDimension(), this.golem.func_110124_au(), this.golem);
/*  46 */     for (Task ticket : list) {
/*  47 */       if (areGolemTagsValidForTask(ticket) && ticket.canGolemPerformTask(this.golem) && this.golem
/*  48 */         .func_180485_d(ticket.getPos()) && 
/*  49 */         isValidDestination(this.golem.field_70170_p, ticket.getPos()) && 
/*  50 */         canEasilyReach(ticket.getPos())) {
/*     */         
/*  52 */         this.targetBlock = getAdjacentSpace(ticket.getPos());
/*  53 */         this.golem.setTask(ticket);
/*  54 */         this.golem.getTask().setReserved(true);
/*  55 */         if (ModConfig.CONFIG_GRAPHICS.showGolemEmotes) this.golem.field_70170_p.func_72960_a(this.golem, (byte)5);
/*     */         
/*  57 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/*  61 */     return false;
/*     */   }
/*     */   
/*     */   private BlockPos getAdjacentSpace(BlockPos pos) {
/*  65 */     double d = Double.MAX_VALUE;
/*  66 */     BlockPos closest = null;
/*  67 */     for (EnumFacing face : EnumFacing.field_176754_o) {
/*  68 */       IBlockState block = this.golem.field_70170_p.func_180495_p(pos.func_177972_a(face));
/*  69 */       if (!block.func_185904_a().func_76230_c()) {
/*  70 */         double dist = pos.func_177972_a(face).func_177957_d(this.golem.field_70165_t, this.golem.field_70163_u, this.golem.field_70161_v);
/*  71 */         if (dist < d) {
/*  72 */           closest = pos.func_177972_a(face);
/*  73 */           d = dist;
/*     */         } 
/*     */       } 
/*     */     } 
/*  77 */     return closest;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canEasilyReach(BlockPos pos) {
/*  83 */     if (this.golem.func_174831_c(pos) < this.minDist) return true;
/*     */     
/*  85 */     Path pathentity = this.golem.func_70661_as().func_75488_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D);
/*     */     
/*  87 */     if (pathentity == null)
/*     */     {
/*  89 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  93 */     PathPoint pathpoint = pathentity.func_75870_c();
/*     */     
/*  95 */     if (pathpoint == null)
/*     */     {
/*  97 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 101 */     int i = pathpoint.field_75839_a - MathHelper.func_76141_d(pos.func_177958_n());
/* 102 */     int j = pathpoint.field_75838_c - MathHelper.func_76141_d(pos.func_177952_p());
/* 103 */     int k = pathpoint.field_75837_b - MathHelper.func_76141_d(pos.func_177956_o());
/*     */     
/* 105 */     if (i == 0 && j == 0 && k == 2) k--;
/*     */     
/* 107 */     return ((i * i + j * j + k * k) < 2.25D);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\ai\AIGotoBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */