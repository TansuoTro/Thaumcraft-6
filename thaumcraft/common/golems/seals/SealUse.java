/*     */ package thaumcraft.common.golems.seals;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.FakePlayer;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftInvHelper;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.GolemHelper;
/*     */ import thaumcraft.api.golems.IGolemAPI;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.common.golems.GolemInteractionHelper;
/*     */ import thaumcraft.common.golems.client.gui.SealBaseContainer;
/*     */ import thaumcraft.common.golems.client.gui.SealBaseGUI;
/*     */ import thaumcraft.common.golems.tasks.TaskHandler;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SealUse
/*     */   extends SealFiltered
/*     */   implements ISealConfigToggles
/*     */ {
/*  37 */   public String getKey() { return "thaumcraft:use"; }
/*     */ 
/*     */   
/*  40 */   int delay = (new Random(System.nanoTime())).nextInt(49);
/*     */   
/*  42 */   int watchedTask = Integer.MIN_VALUE;
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickSeal(World world, ISealEntity seal) {
/*  47 */     if (this.delay++ % 5 != 0)
/*     */       return; 
/*  49 */     Task oldTask = TaskHandler.getTask(world.field_73011_w.getDimension(), this.watchedTask);
/*  50 */     if (oldTask == null || oldTask.isSuspended() || oldTask.isCompleted()) {
/*     */       
/*  52 */       if ((getToggles()[5]).value != world.func_175623_d((seal.getSealPos()).pos))
/*  53 */         return;  Task task = new Task(seal.getSealPos(), (seal.getSealPos()).pos);
/*  54 */       task.setPriority(seal.getPriority());
/*  55 */       TaskHandler.addTask(world.field_73011_w.getDimension(), task);
/*     */       
/*  57 */       this.watchedTask = task.getId();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onTaskStarted(World world, IGolemAPI golem, Task task) {}
/*     */ 
/*     */   
/*     */   public boolean mayPlace(World world, Block blockIn, BlockPos pos, EnumFacing side) {
/*  66 */     IBlockState block = world.func_180495_p(pos);
/*  67 */     AxisAlignedBB axisalignedbb = blockIn.func_185496_a(blockIn.func_176223_P(), world, pos);
/*  68 */     if (axisalignedbb != null && !world.func_72917_a(axisalignedbb, null)) return false; 
/*  69 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onTaskCompletion(World world, IGolemAPI golem, Task task) {
/*  74 */     if ((getToggles()[5]).value == world.func_175623_d(task.getPos())) {
/*     */       
/*  76 */       ItemStack clickStack = (ItemStack)golem.getCarrying().get(0);
/*  77 */       if (!((ItemStack)this.filter.get(0)).func_190926_b()) {
/*  78 */         clickStack = InventoryUtils.findFirstMatchFromFilter(this.filter, this.filterSize, this.blacklist, golem.getCarrying(), new ThaumcraftInvHelper.InvFilter(!(this.props[0]).value, !(this.props[1]).value, (this.props[2]).value, (this.props[3]).value));
/*     */       }
/*     */ 
/*     */       
/*  82 */       if (!clickStack.func_190926_b() || (this.props[6]).value) {
/*  83 */         ItemStack ss = ItemStack.field_190927_a;
/*  84 */         if (!clickStack.func_190926_b()) {
/*  85 */           ss = clickStack.func_77946_l();
/*  86 */           golem.dropItem(clickStack.func_77946_l());
/*     */         } 
/*     */         
/*  89 */         GolemInteractionHelper.golemClick(world, golem, task.getPos(), (task.getSealPos()).face, (this.props[6]).value ? ItemStack.field_190927_a : ss, (this.props[7]).value, 
/*  90 */             !(getToggles()[4]).value);
/*     */       } 
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 147 */     task.setSuspended(true);
/* 148 */     return true;
/*     */   }
/*     */   
/*     */   private void dropSomeItems(FakePlayer fp2, IGolemAPI golem) {
/*     */     int i;
/* 153 */     for (i = 0; i < fp2.field_71071_by.field_70462_a.size(); i++) {
/*     */       
/* 155 */       if (!((ItemStack)fp2.field_71071_by.field_70462_a.get(i)).func_190926_b()) {
/*     */         
/* 157 */         if (golem.canCarry((ItemStack)fp2.field_71071_by.field_70462_a.get(i), true)) {
/* 158 */           fp2.field_71071_by.field_70462_a.set(i, golem.holdItem((ItemStack)fp2.field_71071_by.field_70462_a.get(i)));
/*     */         }
/* 160 */         if (!((ItemStack)fp2.field_71071_by.field_70462_a.get(i)).func_190926_b() && ((ItemStack)fp2.field_71071_by.field_70462_a.get(i)).func_190916_E() > 0)
/* 161 */           InventoryUtils.dropItemAtEntity(golem.getGolemWorld(), (ItemStack)fp2.field_71071_by.field_70462_a.get(i), golem.getGolemEntity()); 
/* 162 */         fp2.field_71071_by.field_70462_a.set(i, ItemStack.field_190927_a);
/*     */       } 
/*     */     } 
/* 165 */     for (i = 0; i < fp2.field_71071_by.field_70460_b.size(); i++) {
/*     */       
/* 167 */       if (!((ItemStack)fp2.field_71071_by.field_70460_b.get(i)).func_190926_b()) {
/*     */         
/* 169 */         if (golem.canCarry((ItemStack)fp2.field_71071_by.field_70460_b.get(i), true)) {
/* 170 */           fp2.field_71071_by.field_70460_b.set(i, golem.holdItem((ItemStack)fp2.field_71071_by.field_70460_b.get(i)));
/*     */         }
/* 172 */         if (!((ItemStack)fp2.field_71071_by.field_70462_a.get(i)).func_190926_b() && ((ItemStack)fp2.field_71071_by.field_70460_b.get(i)).func_190916_E() > 0)
/* 173 */           InventoryUtils.dropItemAtEntity(golem.getGolemWorld(), (ItemStack)fp2.field_71071_by.field_70460_b.get(i), golem.getGolemEntity()); 
/* 174 */         fp2.field_71071_by.field_70460_b.set(i, ItemStack.field_190927_a);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canGolemPerformTask(IGolemAPI golem, Task task) {
/* 181 */     if (!(this.props[6]).value) {
/*     */       
/* 183 */       boolean found = !InventoryUtils.findFirstMatchFromFilter(this.filter, this.filterSize, this.blacklist, golem.getCarrying(), new ThaumcraftInvHelper.InvFilter(!(this.props[0]).value, !(this.props[1]).value, (this.props[2]).value, (this.props[3]).value)).func_190926_b();
/* 184 */       if (!found && (getToggles()[8]).value && !this.blacklist && getInv().get(false) != null) {
/* 185 */         ISealEntity se = SealHandler.getSealEntity((golem.getGolemWorld()).field_73011_w.getDimension(), task.getSealPos());
/* 186 */         if (se != null) {
/* 187 */           ItemStack stack = ((ItemStack)getInv().get(0)).func_77946_l();
/* 188 */           if (!(this.props[0]).value) stack.func_77964_b(32767); 
/* 189 */           GolemHelper.requestProvisioning(golem.getGolemWorld(), se, stack);
/*     */         } 
/*     */       } 
/* 192 */       return found;
/*     */     } 
/* 194 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onTaskSuspension(World world, Task task) {}
/*     */ 
/*     */   
/* 202 */   public boolean canPlaceAt(World world, BlockPos pos, EnumFacing side) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 207 */   public ResourceLocation getSealIcon() { return this.icon; }
/*     */ 
/*     */   
/* 210 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_use");
/*     */ 
/*     */ 
/*     */   
/*     */   public void onRemoval(World world, BlockPos pos, EnumFacing side) {}
/*     */ 
/*     */   
/* 217 */   public Object returnContainer(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal) { return new SealBaseContainer(player.field_71071_by, world, seal); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/* 223 */   public Object returnGui(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal) { return new SealBaseGUI(player.field_71071_by, world, seal); }
/*     */ 
/*     */ 
/*     */   
/* 227 */   public int[] getGuiCategories() { return new int[] { 1, 3, 0, 4 }; }
/*     */ 
/*     */ 
/*     */   
/* 231 */   public EnumGolemTrait[] getRequiredTags() { return new EnumGolemTrait[] { EnumGolemTrait.DEFT, EnumGolemTrait.SMART }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 236 */   public EnumGolemTrait[] getForbiddenTags() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 241 */   public ISealConfigToggles.SealToggle[] getToggles() { return this.props; }
/*     */ 
/*     */   
/* 244 */   protected ISealConfigToggles.SealToggle[] props = { new ISealConfigToggles.SealToggle(true, "pmeta", "golem.prop.meta"), new ISealConfigToggles.SealToggle(true, "pnbt", "golem.prop.nbt"), new ISealConfigToggles.SealToggle(false, "pore", "golem.prop.ore"), new ISealConfigToggles.SealToggle(false, "pmod", "golem.prop.mod"), new ISealConfigToggles.SealToggle(false, "pleft", "golem.prop.left"), new ISealConfigToggles.SealToggle(false, "pempty", "golem.prop.empty"), new ISealConfigToggles.SealToggle(false, "pemptyhand", "golem.prop.emptyhand"), new ISealConfigToggles.SealToggle(false, "psneak", "golem.prop.sneak"), new ISealConfigToggles.SealToggle(false, "ppro", "golem.prop.provision.wl") };
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
/*     */ 
/*     */ 
/*     */   
/* 258 */   public void setToggle(int indx, boolean value) { this.props[indx].setValue(value); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\seals\SealUse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */