/*     */ package thaumcraft.common.golems.seals;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Tuple;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.items.ItemHandlerHelper;
/*     */ import thaumcraft.api.ThaumcraftInvHelper;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.IGolemAPI;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.common.golems.tasks.TaskHandler;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SealFill
/*     */   extends SealFiltered
/*     */ {
/*  31 */   public String getKey() { return "thaumcraft:fill"; }
/*     */ 
/*     */   
/*  34 */   int delay = (new Random(System.nanoTime())).nextInt(50);
/*  35 */   int watchedTask = Integer.MIN_VALUE;
/*     */ 
/*     */   
/*     */   public void tickSeal(World world, ISealEntity seal) {
/*  39 */     if (this.delay++ % 20 != 0)
/*     */       return; 
/*  41 */     Task oldTask = TaskHandler.getTask(world.field_73011_w.getDimension(), this.watchedTask);
/*  42 */     if (oldTask == null || oldTask.isReserved() || oldTask.isSuspended() || oldTask.isCompleted()) {
/*  43 */       Task task = new Task(seal.getSealPos(), (seal.getSealPos()).pos);
/*  44 */       task.setPriority(seal.getPriority());
/*  45 */       TaskHandler.addTask(world.field_73011_w.getDimension(), task);
/*  46 */       this.watchedTask = task.getId();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onTaskStarted(World world, IGolemAPI golem, Task task) {
/*  52 */     ISealEntity se = SealHandler.getSealEntity(world.field_73011_w.getDimension(), task.getSealPos());
/*  53 */     if (se != null && !se.isStoppedByRedstone(world)) {
/*  54 */       Task newTask = new Task(task.getSealPos(), (task.getSealPos()).pos);
/*  55 */       newTask.setPriority(se.getPriority());
/*  56 */       TaskHandler.addTask(world.field_73011_w.getDimension(), newTask);
/*  57 */       this.watchedTask = newTask.getId();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onTaskCompletion(World world, IGolemAPI golem, Task task) {
/*  63 */     ThaumcraftInvHelper.InvFilter filter = new ThaumcraftInvHelper.InvFilter(!(this.props[0]).value, !(this.props[1]).value, (this.props[2]).value, (this.props[3]).value);
/*  64 */     Tuple<ItemStack, Integer> tuple = InventoryUtils.findFirstMatchFromFilterTuple(getInv(), getSizes(), isBlacklist(), golem.getCarrying(), filter);
/*     */     
/*  66 */     if (tuple.func_76341_a() != null && !((ItemStack)tuple.func_76341_a()).func_190926_b()) {
/*  67 */       IItemHandler inv = ThaumcraftInvHelper.getItemHandlerAt(world, (task.getSealPos()).pos, (task.getSealPos()).face);
/*  68 */       int limit = ((ItemStack)tuple.func_76341_a()).func_190916_E();
/*     */       
/*  70 */       if (hasStacksizeLimiters() && tuple.func_76340_b() != null && ((Integer)tuple.func_76340_b()).intValue() > 0) {
/*     */ 
/*     */         
/*  73 */         int c = (inv == null) ? InventoryUtils.countStackInWorld(golem.getGolemWorld(), (task.getSealPos()).pos, (ItemStack)tuple.func_76341_a(), 1.5D, filter) : ThaumcraftInvHelper.countTotalItemsIn(inv, (ItemStack)tuple.func_76341_a(), filter);
/*  74 */         if (c < ((Integer)tuple.func_76340_b()).intValue())
/*  75 */         { limit = ((Integer)tuple.func_76340_b()).intValue() - c; }
/*  76 */         else { limit = 0; }
/*     */       
/*     */       } 
/*  79 */       if (limit > 0) {
/*  80 */         ItemStack t = ((ItemStack)tuple.func_76341_a()).func_77946_l();
/*  81 */         t.func_190920_e(limit);
/*  82 */         ItemStack s = golem.dropItem(t);
/*  83 */         if (inv == null) {
/*     */ 
/*     */ 
/*     */           
/*  87 */           EntityItem ie = new EntityItem(world, (task.getSealPos()).pos.func_177958_n() + 0.5D + (task.getSealPos()).face.func_82601_c(), (task.getSealPos()).pos.func_177956_o() + 0.5D + (task.getSealPos()).face.func_96559_d(), (task.getSealPos()).pos.func_177952_p() + 0.5D + (task.getSealPos()).face.func_82599_e(), s);
/*  88 */           ie.field_70159_w /= 5.0D;
/*  89 */           ie.field_70181_x /= 2.0D;
/*  90 */           ie.field_70179_y /= 5.0D;
/*  91 */           world.func_72838_d(ie);
/*     */         } else {
/*  93 */           golem.holdItem(ItemHandlerHelper.insertItemStacked(inv, s, false));
/*     */         } 
/*  95 */         ((Entity)golem).func_184185_a(SoundEvents.field_187638_cR, 0.125F, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 1.0F);
/*  96 */         golem.addRankXp(1);
/*  97 */         golem.swingArm();
/*     */       } 
/*     */     } 
/* 100 */     task.setSuspended(true);
/* 101 */     return true;
/*     */   }
/*     */   
/* 104 */   protected ISealConfigToggles.SealToggle[] props = { new ISealConfigToggles.SealToggle(true, "pmeta", "golem.prop.meta"), new ISealConfigToggles.SealToggle(true, "pnbt", "golem.prop.nbt"), new ISealConfigToggles.SealToggle(false, "pore", "golem.prop.ore"), new ISealConfigToggles.SealToggle(false, "pmod", "golem.prop.mod"), new ISealConfigToggles.SealToggle(false, "pexist", "golem.prop.exist") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canGolemPerformTask(IGolemAPI golem, Task task) {
/* 114 */     ThaumcraftInvHelper.InvFilter filter = new ThaumcraftInvHelper.InvFilter(!(this.props[0]).value, !(this.props[1]).value, (this.props[2]).value, (this.props[3]).value);
/* 115 */     Tuple<ItemStack, Integer> tuple = InventoryUtils.findFirstMatchFromFilterTuple(getInv(), getSizes(), isBlacklist(), golem.getCarrying(), filter);
/* 116 */     if (tuple.func_76341_a() != null && !((ItemStack)tuple.func_76341_a()).func_190926_b()) {
/* 117 */       IItemHandler inv = ThaumcraftInvHelper.getItemHandlerAt(golem.getGolemWorld(), (task.getSealPos()).pos, (task.getSealPos()).face);
/* 118 */       if (inv != null) {
/* 119 */         if (tuple.func_76341_a() != null && !((ItemStack)tuple.func_76341_a()).func_190926_b() && (this.props[4]).value && ThaumcraftInvHelper.countTotalItemsIn(inv, (ItemStack)tuple.func_76341_a(), filter) <= 0) {
/* 120 */           return false;
/*     */         }
/* 122 */         if (tuple.func_76341_a() != null && !((ItemStack)tuple.func_76341_a()).func_190926_b() && ThaumcraftInvHelper.hasRoomForSome(golem.getGolemWorld(), (task.getSealPos()).pos, (task.getSealPos()).face, (ItemStack)tuple.func_76341_a())) {
/* 123 */           if (hasStacksizeLimiters() && tuple.func_76340_b() != null && ((Integer)tuple.func_76340_b()).intValue() > 0) {
/* 124 */             if (ThaumcraftInvHelper.countTotalItemsIn(inv, (ItemStack)tuple.func_76341_a(), filter) < ((Integer)tuple.func_76340_b()).intValue()) {
/* 125 */               return true;
/*     */             }
/*     */           } else {
/* 128 */             return true;
/*     */           } 
/*     */         }
/* 131 */       } else if (tuple.func_76341_a() != null && !((ItemStack)tuple.func_76341_a()).func_190926_b()) {
/* 132 */         if (hasStacksizeLimiters() && tuple.func_76340_b() != null && ((Integer)tuple.func_76340_b()).intValue() > 0) {
/* 133 */           return (InventoryUtils.countStackInWorld(golem.getGolemWorld(), (task.getSealPos()).pos, (ItemStack)tuple.func_76341_a(), 1.5D, filter) < ((Integer)tuple.func_76340_b()).intValue());
/*     */         }
/* 135 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 140 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   public boolean canPlaceAt(World world, BlockPos pos, EnumFacing side) { return !world.func_175623_d(pos); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   public ResourceLocation getSealIcon() { return this.icon; }
/*     */ 
/*     */   
/* 155 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_fill");
/*     */ 
/*     */ 
/*     */   
/* 159 */   public int[] getGuiCategories() { return new int[] { 1, 0, 4 }; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   public EnumGolemTrait[] getRequiredTags() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public EnumGolemTrait[] getForbiddenTags() { return new EnumGolemTrait[] { EnumGolemTrait.CLUMSY }; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onTaskSuspension(World world, Task task) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onRemoval(World world, BlockPos pos, EnumFacing side) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   public boolean hasStacksizeLimiters() { return !isBlacklist(); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\seals\SealFill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */