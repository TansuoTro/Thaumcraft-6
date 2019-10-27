/*     */ package thaumcraft.common.golems.seals;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
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
/*     */ 
/*     */ 
/*     */ public class SealEmpty
/*     */   extends SealFiltered
/*     */ {
/*  33 */   public String getKey() { return "thaumcraft:empty"; }
/*     */ 
/*     */   
/*  36 */   int delay = (new Random(System.nanoTime())).nextInt(30);
/*  37 */   int filterInc = 0;
/*     */   
/*  39 */   HashMap<Integer, ItemStack> cache = new HashMap();
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickSeal(World world, ISealEntity seal) {
/*  44 */     if (this.delay % 100 == 0) {
/*  45 */       Iterator<Integer> it = this.cache.keySet().iterator();
/*  46 */       while (it.hasNext()) {
/*  47 */         Task t = TaskHandler.getTask(world.field_73011_w.getDimension(), ((Integer)it.next()).intValue());
/*  48 */         if (t == null) {
/*  49 */           it.remove();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  54 */     if (this.delay++ % 20 != 0)
/*     */       return; 
/*  56 */     ItemStack stack = InventoryUtils.findFirstMatchFromFilter(getInv(this.filterInc), isBlacklist(), ThaumcraftInvHelper.getItemHandlerAt(world, (seal.getSealPos()).pos, (seal.getSealPos()).face), 
/*  57 */         (seal.getSealPos()).face, new ThaumcraftInvHelper.InvFilter(!(this.props[0]).value, !(this.props[1]).value, (this.props[2]).value, (this.props[3]).value), (this.props[5]).value);
/*     */     
/*  59 */     if (stack != null && !stack.func_190926_b()) {
/*  60 */       Task task = new Task(seal.getSealPos(), (seal.getSealPos()).pos);
/*  61 */       task.setPriority(seal.getPriority());
/*  62 */       task.setLifespan((short)5);
/*  63 */       TaskHandler.addTask(world.field_73011_w.getDimension(), task);
/*  64 */       this.cache.put(Integer.valueOf(task.getId()), stack);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onTaskCompletion(World world, IGolemAPI golem, Task task) {
/*  71 */     ItemStack stack = (ItemStack)this.cache.get(Integer.valueOf(task.getId()));
/*  72 */     int sa = ThaumcraftInvHelper.countTotalItemsIn(world, (task.getSealPos()).pos, (task.getSealPos()).face, stack, new ThaumcraftInvHelper.InvFilter(!(this.props[0]).value, !(this.props[1]).value, (this.props[2]).value, (this.props[3]).value));
/*  73 */     if (stack != null && !stack.func_190926_b() && (this.props[5]).value && sa <= stack.func_190916_E()) {
/*  74 */       stack = stack.func_77946_l();
/*  75 */       stack.func_190920_e(sa - 1);
/*     */     } 
/*  77 */     if (stack != null && !stack.func_190926_b()) {
/*  78 */       int limit = golem.canCarryAmount(stack);
/*  79 */       if (limit > 0) {
/*  80 */         ItemStack s = golem.holdItem(
/*  81 */             InventoryUtils.removeStackFrom(world, (task.getSealPos()).pos, (task.getSealPos()).face, 
/*  82 */               InventoryUtils.copyLimitedStack(stack, limit), new ThaumcraftInvHelper.InvFilter(!(this.props[0]).value, !(this.props[1]).value, (this.props[2]).value, (this.props[3]).value), false));
/*     */         
/*  84 */         if (!s.func_190926_b()) {
/*  85 */           InventoryUtils.ejectStackAt(world, (task.getSealPos()).pos.func_177972_a((task.getSealPos()).face), (task.getSealPos()).face.func_176734_d(), s);
/*     */         }
/*  87 */         ((Entity)golem).func_184185_a(SoundEvents.field_187638_cR, 0.125F, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*  88 */         golem.swingArm();
/*     */       } 
/*     */     } 
/*  91 */     this.cache.remove(Integer.valueOf(task.getId()));
/*  92 */     this.filterInc++;
/*  93 */     task.setSuspended(true);
/*  94 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canGolemPerformTask(IGolemAPI golem, Task task) {
/*  99 */     ItemStack stack = (ItemStack)this.cache.get(Integer.valueOf(task.getId()));
/* 100 */     return (stack != null && !stack.func_190926_b() && golem.canCarry(stack, true));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceAt(World world, BlockPos pos, EnumFacing side) {
/* 105 */     IItemHandler inv = ThaumcraftInvHelper.getItemHandlerAt(world, pos, side);
/* 106 */     if (inv != null) {
/* 107 */       return true;
/*     */     }
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */   
/* 113 */   public NonNullList<ItemStack> getInv(int c) { return getInv(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public ResourceLocation getSealIcon() { return this.icon; }
/*     */ 
/*     */   
/* 121 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_empty");
/*     */ 
/*     */ 
/*     */   
/* 125 */   public int[] getGuiCategories() { return new int[] { 1, 0, 4 }; }
/*     */ 
/*     */   
/* 128 */   protected ISealConfigToggles.SealToggle[] props = { new ISealConfigToggles.SealToggle(true, "pmeta", "golem.prop.meta"), new ISealConfigToggles.SealToggle(true, "pnbt", "golem.prop.nbt"), new ISealConfigToggles.SealToggle(false, "pore", "golem.prop.ore"), new ISealConfigToggles.SealToggle(false, "pmod", "golem.prop.mod"), new ISealConfigToggles.SealToggle(false, "pcycle", "golem.prop.cycle"), new ISealConfigToggles.SealToggle(false, "pleave", "golem.prop.leave") };
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
/* 139 */   public EnumGolemTrait[] getRequiredTags() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public EnumGolemTrait[] getForbiddenTags() { return new EnumGolemTrait[] { EnumGolemTrait.CLUMSY }; }
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
/* 155 */   public void onTaskSuspension(World world, Task task) { this.cache.remove(Integer.valueOf(task.getId())); }
/*     */   
/*     */   public void onRemoval(World world, BlockPos pos, EnumFacing side) {}
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\seals\SealEmpty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */