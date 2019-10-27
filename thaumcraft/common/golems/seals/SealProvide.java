/*     */ package thaumcraft.common.golems.seals;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.IEntityOwnable;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import thaumcraft.api.ThaumcraftInvHelper;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.GolemHelper;
/*     */ import thaumcraft.api.golems.IGolemAPI;
/*     */ import thaumcraft.api.golems.ProvisionRequest;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.common.golems.EntityThaumcraftGolem;
/*     */ import thaumcraft.common.golems.tasks.TaskHandler;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SealProvide
/*     */   extends SealFiltered
/*     */   implements ISealConfigToggles
/*     */ {
/*  36 */   public String getKey() { return "thaumcraft:provider"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public int getFilterSize() { return 9; }
/*     */ 
/*     */   
/*  44 */   int delay = (new Random(System.nanoTime())).nextInt(88);
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickSeal(World world, ISealEntity seal) {
/*  49 */     if (this.delay % 100 == 0 && GolemHelper.provisionRequests.containsKey(Integer.valueOf(world.field_73011_w.getDimension()))) {
/*  50 */       Iterator<ProvisionRequest> it = ((ArrayList)GolemHelper.provisionRequests.get(Integer.valueOf(world.field_73011_w.getDimension()))).iterator();
/*  51 */       while (it.hasNext()) {
/*  52 */         ProvisionRequest pr = (ProvisionRequest)it.next();
/*  53 */         if (pr.isInvalid() || pr.getLinkedTask() == null || pr.getLinkedTask().isSuspended() || pr
/*  54 */           .getLinkedTask().isCompleted() || pr.getTimeout() < System.currentTimeMillis()) {
/*  55 */           it.remove();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  60 */     if (this.delay++ % 20 != 0)
/*     */       return; 
/*  62 */     IItemHandler inv = ThaumcraftInvHelper.getItemHandlerAt(world, (seal.getSealPos()).pos, (seal.getSealPos()).face);
/*  63 */     if (inv != null && GolemHelper.provisionRequests.containsKey(Integer.valueOf(world.field_73011_w.getDimension()))) {
/*  64 */       ListIterator<ProvisionRequest> it = ((ArrayList)GolemHelper.provisionRequests.get(Integer.valueOf(world.field_73011_w.getDimension()))).listIterator();
/*  65 */       while (it.hasNext()) {
/*  66 */         ProvisionRequest pr = (ProvisionRequest)it.next();
/*  67 */         if (pr.isInvalid()) {
/*  68 */           it.remove();
/*     */           continue;
/*     */         } 
/*  71 */         if (pr.getLinkedTask() != null) {
/*     */           continue;
/*     */         }
/*  74 */         if ((pr.getSeal() != null && (pr.getSeal().getSealPos()).pos.func_177951_i((seal.getSealPos()).pos) < 4096.0D) || (pr
/*  75 */           .getEntity() != null && (seal.getSealPos()).pos.func_177954_c((pr.getEntity()).field_70165_t, (pr.getEntity()).field_70163_u, (pr.getEntity()).field_70161_v) < 4096.0D) || (pr
/*  76 */           .getPos() != null && (seal.getSealPos()).pos.func_177951_i(pr.getPos()) < 4096.0D)) {
/*  77 */           NonNullList<ItemStack> stacks = NonNullList.func_191197_a(1, pr.getStack());
/*     */           
/*  79 */           if (!InventoryUtils.findFirstMatchFromFilter(getInv(), getSizes(), this.blacklist, stacks, new ThaumcraftInvHelper.InvFilter(!(this.props[0]).value, !(this.props[1]).value, (this.props[2]).value, (this.props[3]).value)).func_190926_b() && 
/*  80 */             ThaumcraftInvHelper.countTotalItemsIn(inv, pr.getStack(), ThaumcraftInvHelper.InvFilter.STRICT) > ((this.props[5]).value ? 1 : 0)) {
/*     */             
/*  82 */             Task task = new Task(seal.getSealPos(), (seal.getSealPos()).pos);
/*  83 */             task.setPriority((pr.getSeal() != null) ? pr.getSeal().getPriority() : 5);
/*  84 */             task.setLifespan((pr.getSeal() != null) ? 10 : 31000);
/*  85 */             TaskHandler.addTask(world.field_73011_w.getDimension(), task);
/*  86 */             pr.setLinkedTask(task);
/*  87 */             task.setLinkedProvision(pr);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  97 */   public boolean matchesFilters(ItemStack stack) { return InventoryUtils.matchesFilters(getInv(), this.blacklist, stack, new ThaumcraftInvHelper.InvFilter(!(this.props[0]).value, !(this.props[1]).value, (this.props[2]).value, (this.props[3]).value)); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onTaskCompletion(World world, IGolemAPI golem, Task task) {
/* 103 */     if (task.getLinkedProvision() != null) {
/* 104 */       if (task.getData() == 0) {
/* 105 */         IItemHandler inv = ThaumcraftInvHelper.getItemHandlerAt(world, (task.getSealPos()).pos, (task.getSealPos()).face);
/* 106 */         if (inv != null) {
/*     */           
/* 108 */           ItemStack stack = ItemStack.field_190927_a;
/*     */           try {
/* 110 */             stack = task.getLinkedProvision().getStack().func_77946_l();
/* 111 */           } catch (Exception exception) {}
/*     */           
/* 113 */           if (stack != null && (this.props[4]).value) stack.func_190920_e(1);
/*     */           
/* 115 */           int sa = 0;
/* 116 */           if (stack != null && !stack.func_190926_b() && (this.props[5]).value && (sa = ThaumcraftInvHelper.countTotalItemsIn(inv, stack, ThaumcraftInvHelper.InvFilter.STRICT)) <= stack.func_190916_E()) {
/* 117 */             stack.func_190920_e(sa - 1);
/*     */           }
/* 119 */           if (stack != null && !stack.func_190926_b()) {
/* 120 */             int limit = golem.canCarryAmount(stack);
/* 121 */             if (limit > 0) {
/* 122 */               ItemStack s = golem.holdItem(InventoryUtils.removeStackFrom(inv, 
/* 123 */                     InventoryUtils.copyLimitedStack(stack, limit), ThaumcraftInvHelper.InvFilter.STRICT, false));
/*     */ 
/*     */               
/* 126 */               if (s != null && !s.func_190926_b()) {
/* 127 */                 InventoryUtils.ejectStackAt(world, (task.getSealPos()).pos.func_177972_a((task.getSealPos()).face), (task.getSealPos()).face.func_176734_d(), s);
/*     */               }
/*     */               
/* 130 */               ((Entity)golem).func_184185_a(SoundEvents.field_187638_cR, 0.125F, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/* 131 */               golem.addRankXp(1);
/* 132 */               golem.swingArm();
/* 133 */               ProvisionRequest pr2 = task.getLinkedProvision();
/*     */               
/* 135 */               if (pr2.getEntity() != null || pr2.getPos() != null) {
/* 136 */                 Task task2 = null;
/* 137 */                 if (pr2.getEntity() != null) {
/* 138 */                   task2 = new Task(task.getSealPos(), pr2.getEntity());
/*     */                 } else {
/* 140 */                   task2 = new Task(task.getSealPos(), pr2.getPos());
/* 141 */                 }  task2.setPriority(task.getPriority());
/* 142 */                 task2.setData((pr2.getEntity() != null) ? 1 : 2);
/* 143 */                 task2.setLifespan((short)31000);
/* 144 */                 TaskHandler.addTask(world.field_73011_w.getDimension(), task2);
/* 145 */                 pr2.setLinkedTask(task2);
/* 146 */                 task2.setLinkedProvision(pr2);
/*     */               }
/*     */             
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         } 
/* 154 */       } else if (task.getLinkedProvision() != null) {
/* 155 */         ProvisionRequest pr = task.getLinkedProvision();
/* 156 */         ItemStack cs = pr.getStack();
/* 157 */         ItemStack s = golem.dropItem(cs);
/*     */         
/* 159 */         if (s.func_190916_E() < cs.func_190916_E()) {
/* 160 */           ItemStack ps = cs.func_77946_l();
/* 161 */           ps.func_190920_e(cs.func_190916_E() - s.func_190916_E());
/* 162 */           if (task.getData() == 1) {
/* 163 */             GolemHelper.requestProvisioning(world, pr.getEntity(), ps);
/*     */           } else {
/* 165 */             GolemHelper.requestProvisioning(world, pr.getPos(), pr.getSide(), ps);
/*     */           } 
/*     */         } 
/* 168 */         if (task.getData() == 1) {
/*     */           
/* 170 */           InventoryUtils.dropItemAtEntity(world, s, pr.getEntity());
/*     */         } else {
/*     */           
/* 173 */           ItemStack back = InventoryUtils.ejectStackAt(world, pr.getPos().func_177972_a(pr.getSide()), pr.getSide().func_176734_d(), s, true);
/* 174 */           if (!back.func_190926_b()) {
/* 175 */             golem.holdItem(back);
/*     */           }
/*     */         } 
/*     */         
/* 179 */         ((Entity)golem).func_184185_a(SoundEvents.field_187638_cR, 0.125F, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 1.0F);
/* 180 */         golem.swingArm();
/* 181 */         pr.setInvalid(true);
/*     */       } 
/*     */     }
/*     */     
/* 185 */     task.setSuspended(true);
/* 186 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canGolemPerformTask(IGolemAPI golem, Task task) {
/* 191 */     ProvisionRequest pr = task.getLinkedProvision();
/*     */ 
/*     */ 
/*     */     
/* 195 */     boolean b = (pr != null && ((pr.getSeal() != null && ((EntityThaumcraftGolem)golem).func_180485_d((pr.getSeal().getSealPos()).pos)) || (pr.getEntity() != null && ((EntityThaumcraftGolem)golem).func_180485_d(pr.getEntity().func_180425_c())) || (pr.getPos() != null && ((EntityThaumcraftGolem)golem).func_180485_d(pr.getPos()))));
/* 196 */     if (task.getData() == 0) {
/* 197 */       return (b && areGolemTagsValidForTask(pr.getSeal(), golem) && pr
/* 198 */         .getStack() != null && !golem.isCarrying(pr.getStack()) && golem.canCarry(pr.getStack(), true));
/*     */     }
/* 200 */     return (b && areGolemTagsValidForTask(pr.getSeal(), golem) && pr.getStack() != null && golem.isCarrying(pr.getStack()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean areGolemTagsValidForTask(ISealEntity se, IGolemAPI golem) {
/* 206 */     if (se != null) {
/* 207 */       if (se.isLocked() && !((IEntityOwnable)golem).func_184753_b().equals(se.getOwner())) return false; 
/* 208 */       if (se.getSeal().getRequiredTags() != null && 
/* 209 */         !golem.getProperties().getTraits().containsAll(Arrays.asList(se.getSeal().getRequiredTags()))) return false; 
/* 210 */       if (se.getSeal().getForbiddenTags() != null)
/* 211 */         for (EnumGolemTrait tag : se.getSeal().getForbiddenTags()) {
/* 212 */           if (golem.getProperties().getTraits().contains(tag)) return false;
/*     */         
/*     */         }  
/*     */     } else {
/* 216 */       return true;
/*     */     } 
/* 218 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onTaskSuspension(World world, Task task) {
/* 223 */     if (task.getLinkedProvision() != null) task.getLinkedProvision().setLinkedTask(null); 
/* 224 */     task.setLinkedProvision(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceAt(World world, BlockPos pos, EnumFacing side) {
/* 229 */     IItemHandler inv = ThaumcraftInvHelper.getItemHandlerAt(world, pos, side);
/* 230 */     if (inv != null) {
/* 231 */       return true;
/*     */     }
/* 233 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 238 */   public ResourceLocation getSealIcon() { return this.icon; }
/*     */ 
/*     */   
/* 241 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_provider");
/*     */ 
/*     */ 
/*     */   
/* 245 */   public int[] getGuiCategories() { return new int[] { 1, 3, 0, 4 }; }
/*     */ 
/*     */   
/* 248 */   protected ISealConfigToggles.SealToggle[] props = { new ISealConfigToggles.SealToggle(true, "pmeta", "golem.prop.meta"), new ISealConfigToggles.SealToggle(true, "pnbt", "golem.prop.nbt"), new ISealConfigToggles.SealToggle(false, "pore", "golem.prop.ore"), new ISealConfigToggles.SealToggle(false, "pmod", "golem.prop.mod"), new ISealConfigToggles.SealToggle(false, "psing", "golem.prop.single"), new ISealConfigToggles.SealToggle(false, "pleave", "golem.prop.leave") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 259 */   public EnumGolemTrait[] getRequiredTags() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 264 */   public EnumGolemTrait[] getForbiddenTags() { return new EnumGolemTrait[] { EnumGolemTrait.CLUMSY }; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onTaskStarted(World world, IGolemAPI golem, Task task) {}
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
/* 283 */   public ISealConfigToggles.SealToggle[] getToggles() { return this.props; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 288 */   public void setToggle(int indx, boolean value) { this.props[indx].setValue(value); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\seals\SealProvide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */