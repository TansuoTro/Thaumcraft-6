/*     */ package thaumcraft.common.golems.seals;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.GolemHelper;
/*     */ import thaumcraft.api.golems.IGolemAPI;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealConfigArea;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.ISealGui;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.common.golems.client.gui.SealBaseContainer;
/*     */ import thaumcraft.common.golems.client.gui.SealBaseGUI;
/*     */ import thaumcraft.common.golems.tasks.TaskHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SealGuard
/*     */   implements ISeal, ISealGui, ISealConfigArea
/*     */ {
/*  40 */   public String getKey() { return "thaumcraft:guard"; }
/*     */ 
/*     */   
/*  43 */   int delay = (new Random(System.nanoTime())).nextInt(22);
/*     */ 
/*     */   
/*     */   public void tickSeal(World world, ISealEntity seal) {
/*  47 */     if (this.delay++ % 20 != 0)
/*     */       return; 
/*  49 */     AxisAlignedBB area = GolemHelper.getBoundsForArea(seal);
/*     */     
/*  51 */     List list = world.func_72872_a(EntityLivingBase.class, area);
/*  52 */     if (list.size() > 0) {
/*  53 */       for (Object e : list) {
/*  54 */         EntityLivingBase target = (EntityLivingBase)e;
/*     */         
/*  56 */         if (isValidTarget(target)) {
/*  57 */           Task task = new Task(seal.getSealPos(), target);
/*  58 */           task.setPriority(seal.getPriority());
/*  59 */           task.setLifespan((short)10);
/*  60 */           TaskHandler.addTask(world.field_73011_w.getDimension(), task);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*  66 */   protected ISealConfigToggles.SealToggle[] props = { new ISealConfigToggles.SealToggle(true, "pmob", "golem.prop.mob"), new ISealConfigToggles.SealToggle(false, "panimal", "golem.prop.animal"), new ISealConfigToggles.SealToggle(false, "pplayer", "golem.prop.player") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isValidTarget(EntityLivingBase target) {
/*  73 */     boolean valid = false;
/*  74 */     if ((this.props[0]).value && (target instanceof net.minecraft.entity.monster.IMob || target instanceof net.minecraft.entity.monster.EntityMob)) {
/*  75 */       valid = true;
/*     */     }
/*     */     
/*  78 */     if ((this.props[1]).value && (target instanceof net.minecraft.entity.passive.EntityAnimal || target instanceof net.minecraft.entity.passive.IAnimals)) {
/*  79 */       valid = true;
/*     */     }
/*     */     
/*  82 */     if ((this.props[2]).value && FMLCommonHandler.instance().getMinecraftServerInstance().func_71219_W() && target instanceof EntityPlayer) {
/*  83 */       valid = true;
/*     */     }
/*     */     
/*  86 */     return valid;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onTaskStarted(World world, IGolemAPI golem, Task task) {
/*  92 */     if (task.getEntity() != null && task.getEntity() instanceof EntityLivingBase && isValidTarget((EntityLivingBase)task.getEntity())) {
/*  93 */       ((EntityLiving)golem).func_70624_b((EntityLivingBase)task.getEntity());
/*  94 */       golem.addRankXp(1);
/*     */     } 
/*     */     
/*  97 */     task.setSuspended(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onTaskCompletion(World world, IGolemAPI golem, Task task) {
/* 102 */     task.setSuspended(true);
/* 103 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 108 */   public boolean canGolemPerformTask(IGolemAPI golem, Task task) { return !golem.getGolemEntity().func_184191_r((EntityLivingBase)task.getEntity()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public boolean canPlaceAt(World world, BlockPos pos, EnumFacing side) { return !world.func_175623_d(pos); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public ResourceLocation getSealIcon() { return this.icon; }
/*     */ 
/*     */   
/* 121 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_guard");
/*     */ 
/*     */ 
/*     */   
/* 125 */   public int[] getGuiCategories() { return new int[] { 2, 0, 4 }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public EnumGolemTrait[] getRequiredTags() { return new EnumGolemTrait[] { EnumGolemTrait.FIGHTER }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public EnumGolemTrait[] getForbiddenTags() { return null; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onTaskSuspension(World world, Task task) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onRemoval(World world, BlockPos pos, EnumFacing side) {}
/*     */ 
/*     */   
/* 154 */   public Object returnContainer(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal) { return new SealBaseContainer(player.field_71071_by, world, seal); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/* 160 */   public Object returnGui(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal) { return new SealBaseGUI(player.field_71071_by, world, seal); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\seals\SealGuard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */