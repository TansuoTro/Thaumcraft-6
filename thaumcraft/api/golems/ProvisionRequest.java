/*     */ package thaumcraft.api.golems;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ 
/*     */ public class ProvisionRequest {
/*     */   private ISealEntity seal;
/*     */   private Entity entity;
/*     */   private BlockPos pos;
/*     */   private EnumFacing side;
/*     */   private ItemStack stack;
/*     */   
/*     */   ProvisionRequest(ISealEntity seal, ItemStack stack) {
/*  17 */     this.ui = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  23 */     this.seal = seal;
/*  24 */     this.stack = stack.func_77946_l();
/*  25 */     String s = (seal.getSealPos()).pos.toString() + (seal.getSealPos()).face.name() + stack.toString();
/*  26 */     if (stack.func_77942_o()) s = s + stack.func_77978_p().toString(); 
/*  27 */     this.id = s.hashCode();
/*  28 */     this.timeout = System.currentTimeMillis() + 10000L;
/*     */   } private int id; private int ui; private Task linkedTask; private boolean invalid; private long timeout;
/*     */   ProvisionRequest(BlockPos pos, EnumFacing side, ItemStack stack) {
/*     */     this.ui = 0;
/*  32 */     this.pos = pos;
/*  33 */     this.side = side;
/*  34 */     this.stack = stack.func_77946_l();
/*  35 */     String s = pos.toString() + side.name() + stack.toString();
/*  36 */     if (stack.func_77942_o()) s = s + stack.func_77978_p().toString(); 
/*  37 */     this.id = s.hashCode();
/*  38 */     this.timeout = System.currentTimeMillis() + 10000L;
/*     */   }
/*     */   ProvisionRequest(Entity entity, ItemStack stack) {
/*     */     this.ui = 0;
/*  42 */     this.entity = entity;
/*  43 */     this.stack = stack.func_77946_l();
/*  44 */     String s = entity.func_145782_y() + stack.toString();
/*  45 */     if (stack.func_77942_o()) s = s + stack.func_77978_p().toString(); 
/*  46 */     this.id = s.hashCode();
/*  47 */     this.timeout = System.currentTimeMillis() + 10000L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public long getTimeout() { return this.timeout; }
/*     */ 
/*     */ 
/*     */   
/*  57 */   public int getId() { return this.id; }
/*     */ 
/*     */ 
/*     */   
/*  61 */   public void setId(int id) { this.id = id; }
/*     */ 
/*     */ 
/*     */   
/*  65 */   public void setUI(int ui) { this.ui = ui; }
/*     */ 
/*     */ 
/*     */   
/*  69 */   public ISealEntity getSeal() { return this.seal; }
/*     */ 
/*     */ 
/*     */   
/*  73 */   public Entity getEntity() { return this.entity; }
/*     */ 
/*     */ 
/*     */   
/*  77 */   public ItemStack getStack() { return this.stack; }
/*     */ 
/*     */ 
/*     */   
/*  81 */   public BlockPos getPos() { return this.pos; }
/*     */ 
/*     */ 
/*     */   
/*  85 */   public void setPos(BlockPos pos) { this.pos = pos; }
/*     */ 
/*     */ 
/*     */   
/*  89 */   public EnumFacing getSide() { return this.side; }
/*     */ 
/*     */ 
/*     */   
/*  93 */   public void setSide(EnumFacing side) { this.side = side; }
/*     */ 
/*     */ 
/*     */   
/*  97 */   public Task getLinkedTask() { return this.linkedTask; }
/*     */ 
/*     */   
/*     */   public void setLinkedTask(Task linkedTask) {
/* 101 */     this.linkedTask = linkedTask;
/* 102 */     this.timeout = System.currentTimeMillis() + 120000L;
/*     */   }
/*     */ 
/*     */   
/* 106 */   public boolean isInvalid() { return this.invalid; }
/*     */ 
/*     */ 
/*     */   
/* 110 */   public void setInvalid(boolean invalid) { this.invalid = invalid; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/* 116 */     if (this == p_equals_1_)
/*     */     {
/* 118 */       return true;
/*     */     }
/* 120 */     if (!(p_equals_1_ instanceof ProvisionRequest))
/*     */     {
/* 122 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 126 */     ProvisionRequest pr = (ProvisionRequest)p_equals_1_;
/* 127 */     return (this.id == pr.id && this.ui == pr.ui);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isItemStackEqual(ItemStack first, ItemStack other) {
/* 133 */     return (first.func_190916_E() != other.func_190916_E()) ? false : (
/* 134 */       (first.func_77973_b() != other.func_77973_b()) ? false : (
/* 135 */       (first.func_77952_i() != other.func_77952_i()) ? false : ((first
/* 136 */       .func_77978_p() == null && other.func_77978_p() != null) ? false : ((first
/* 137 */       .func_77978_p() == null || first.func_77978_p().equals(other.func_77978_p()))))));
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\golems\ProvisionRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */