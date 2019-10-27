/*     */ package thaumcraft.common.golems.seals;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftInvHelper;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.GolemHelper;
/*     */ import thaumcraft.api.golems.IGolemAPI;
/*     */ import thaumcraft.api.golems.seals.ISealConfigArea;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.golems.EntityThaumcraftGolem;
/*     */ import thaumcraft.common.golems.tasks.TaskHandler;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SealPickup
/*     */   extends SealFiltered
/*     */   implements ISealConfigArea
/*     */ {
/*  37 */   public String getKey() { return "thaumcraft:pickup"; }
/*     */ 
/*     */   
/*  40 */   int delay = (new Random(System.nanoTime())).nextInt(100);
/*  41 */   HashMap<Integer, Integer> itemEntities = new HashMap();
/*     */ 
/*     */   
/*     */   public void tickSeal(World world, ISealEntity seal) {
/*  45 */     if (this.delay++ % 5 != 0)
/*     */       return; 
/*  47 */     AxisAlignedBB area = GolemHelper.getBoundsForArea(seal);
/*     */     
/*  49 */     List list = world.func_72872_a(EntityItem.class, area);
/*  50 */     if (list.size() > 0) {
/*  51 */       for (Object e : list) {
/*  52 */         EntityItem ent = (EntityItem)e;
/*  53 */         if (ent != null && ent.field_70122_E && !ent.func_174874_s() && ent.func_92059_d() != null && 
/*  54 */           !this.itemEntities.containsValue(Integer.valueOf(ent.func_145782_y()))) {
/*  55 */           ItemStack stack = InventoryUtils.findFirstMatchFromFilter(this.filter, this.filterSize, isBlacklist(), NonNullList.func_191197_a(1, ent.func_92059_d()), new ThaumcraftInvHelper.InvFilter(!(this.props[0]).value, !(this.props[1]).value, (this.props[2]).value, (this.props[3]).value));
/*     */           
/*  57 */           if (stack != null && !stack.func_190926_b()) {
/*  58 */             Task task = new Task(seal.getSealPos(), ent);
/*  59 */             task.setPriority(seal.getPriority());
/*  60 */             this.itemEntities.put(Integer.valueOf(task.getId()), Integer.valueOf(ent.func_145782_y()));
/*  61 */             TaskHandler.addTask(world.field_73011_w.getDimension(), task);
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*  68 */     if (this.delay % 100 != 0) {
/*  69 */       Iterator<Integer> it = this.itemEntities.values().iterator();
/*  70 */       while (it.hasNext()) {
/*  71 */         Entity e = world.func_73045_a(((Integer)it.next()).intValue());
/*  72 */         if (e == null || e.field_70128_L) {
/*     */           try {
/*  74 */             it.remove();
/*  75 */           } catch (Exception exception) {}
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean onTaskCompletion(World world, IGolemAPI golem, Task task) {
/*  82 */     EntityItem ei = getItemEntity(world, task);
/*  83 */     if (ei != null && !ei.func_92059_d().func_190926_b()) {
/*  84 */       ItemStack stack = InventoryUtils.findFirstMatchFromFilter(this.filter, this.filterSize, isBlacklist(), NonNullList.func_191197_a(1, ei.func_92059_d()), new ThaumcraftInvHelper.InvFilter(!(this.props[0]).value, !(this.props[1]).value, (this.props[2]).value, (this.props[3]).value));
/*     */       
/*  86 */       if (stack != null && !stack.func_190926_b()) {
/*  87 */         ItemStack is = golem.holdItem(ei.func_92059_d());
/*  88 */         if (is != null && !is.func_190926_b() && is.func_190916_E() > 0) {
/*  89 */           ei.func_92058_a(is);
/*     */         }
/*  91 */         if (is == null || is.func_190926_b() || is.func_190916_E() <= 0) {
/*  92 */           ei.func_70106_y();
/*     */         }
/*     */         
/*  95 */         ((Entity)golem).func_184185_a(SoundEvents.field_187638_cR, 0.125F, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*  96 */         golem.swingArm();
/*     */       } 
/*     */     } 
/*  99 */     task.setSuspended(true);
/* 100 */     this.itemEntities.remove(Integer.valueOf(task.getId()));
/*     */     
/* 102 */     ArrayList<Task> localTasks = TaskHandler.getEntityTasksSorted(world.field_73011_w.getDimension(), null, (Entity)golem);
/* 103 */     for (Task ticket : localTasks) {
/* 104 */       if (this.itemEntities.containsKey(Integer.valueOf(ticket.getId())) && ticket.canGolemPerformTask(golem) && ((EntityThaumcraftGolem)golem)
/* 105 */         .func_180485_d(ticket.getEntity().func_180425_c())) {
/* 106 */         ((EntityThaumcraftGolem)golem).setTask(ticket);
/* 107 */         ((EntityThaumcraftGolem)golem).getTask().setReserved(true);
/* 108 */         if (ModConfig.CONFIG_GRAPHICS.showGolemEmotes) world.func_72960_a((EntityThaumcraftGolem)golem, (byte)5);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 113 */     return true;
/*     */   }
/*     */   
/*     */   protected EntityItem getItemEntity(World world, Task task) {
/* 117 */     Integer ei = (Integer)this.itemEntities.get(Integer.valueOf(task.getId()));
/* 118 */     if (ei != null) {
/* 119 */       Entity ent = world.func_73045_a(ei.intValue());
/* 120 */       if (ent != null && ent instanceof EntityItem) {
/* 121 */         return (EntityItem)ent;
/*     */       }
/*     */     } 
/* 124 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canGolemPerformTask(IGolemAPI golem, Task task) {
/* 129 */     EntityItem ei = getItemEntity(golem.getGolemWorld(), task);
/* 130 */     if (ei != null && ei.func_92059_d() != null) {
/* 131 */       if (ei.field_70128_L) {
/* 132 */         task.setSuspended(true);
/* 133 */         return false;
/*     */       } 
/* 135 */       return golem.canCarry(ei.func_92059_d(), true);
/*     */     } 
/* 137 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 142 */   public boolean canPlaceAt(World world, BlockPos pos, EnumFacing side) { return !world.func_175623_d(pos); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   public ResourceLocation getSealIcon() { return this.icon; }
/*     */ 
/*     */   
/* 150 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_pickup");
/*     */ 
/*     */ 
/*     */   
/* 154 */   public int[] getGuiCategories() { return new int[] { 2, 1, 0, 4 }; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public EnumGolemTrait[] getRequiredTags() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   public EnumGolemTrait[] getForbiddenTags() { return new EnumGolemTrait[] { EnumGolemTrait.CLUMSY }; }
/*     */ 
/*     */   
/* 168 */   protected ISealConfigToggles.SealToggle[] props = { new ISealConfigToggles.SealToggle(true, "pmeta", "golem.prop.meta"), new ISealConfigToggles.SealToggle(true, "pnbt", "golem.prop.nbt"), new ISealConfigToggles.SealToggle(false, "pore", "golem.prop.ore"), new ISealConfigToggles.SealToggle(false, "pmod", "golem.prop.mod") };
/*     */   
/*     */   public void onTaskStarted(World world, IGolemAPI golem, Task task) {}
/*     */   
/*     */   public void onTaskSuspension(World world, Task task) {}
/*     */   
/*     */   public void onRemoval(World world, BlockPos pos, EnumFacing side) {}
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\seals\SealPickup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */