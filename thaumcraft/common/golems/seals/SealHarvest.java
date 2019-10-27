/*     */ package thaumcraft.common.golems.seals;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDirectional;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.EnumPacketDirection;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ import net.minecraftforge.common.util.FakePlayer;
/*     */ import net.minecraftforge.common.util.FakePlayerFactory;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.GolemHelper;
/*     */ import thaumcraft.api.golems.IGolemAPI;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealConfigArea;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.ISealGui;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.common.golems.GolemInteractionHelper;
/*     */ import thaumcraft.common.golems.client.gui.SealBaseContainer;
/*     */ import thaumcraft.common.golems.client.gui.SealBaseGUI;
/*     */ import thaumcraft.common.golems.tasks.TaskHandler;
/*     */ import thaumcraft.common.lib.network.FakeNetHandlerPlayServer;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.CropUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SealHarvest
/*     */   implements ISeal, ISealGui, ISealConfigArea, ISealConfigToggles
/*     */ {
/*  60 */   public String getKey() { return "thaumcraft:harvest"; }
/*     */ 
/*     */   
/*  63 */   int delay = (new Random(System.nanoTime())).nextInt(33);
/*  64 */   int count = 0;
/*  65 */   HashMap<Long, ReplantInfo> replantTasks = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickSeal(World world, ISealEntity seal) {
/*  71 */     if (this.delay % 100 == 0) {
/*  72 */       AxisAlignedBB area = GolemHelper.getBoundsForArea(seal);
/*     */       
/*  74 */       Iterator<Long> rt = this.replantTasks.keySet().iterator();
/*  75 */       while (rt.hasNext()) {
/*  76 */         BlockPos pp = BlockPos.func_177969_a(((Long)rt.next()).longValue());
/*  77 */         if (!area.func_72318_a(new Vec3d(pp.func_177958_n() + 0.5D, pp.func_177956_o() + 0.5D, pp.func_177952_p() + 0.5D))) {
/*  78 */           if (this.replantTasks.get(rt) != null) {
/*  79 */             Task tt = TaskHandler.getTask(world.field_73011_w.getDimension(), ((ReplantInfo)this.replantTasks.get(rt)).taskid);
/*  80 */             if (tt != null) tt.setSuspended(true); 
/*     */           } 
/*  82 */           rt.remove();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  87 */     if (this.delay++ % 5 != 0)
/*     */       return; 
/*  89 */     BlockPos p = GolemHelper.getPosInArea(seal, this.count++);
/*     */     
/*  91 */     if (CropUtils.isGrownCrop(world, p)) {
/*  92 */       Task task = new Task(seal.getSealPos(), p);
/*  93 */       task.setPriority(seal.getPriority());
/*  94 */       TaskHandler.addTask(world.field_73011_w.getDimension(), task);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 101 */     else if ((getToggles()[0]).value && this.replantTasks.containsKey(Long.valueOf(p.func_177986_g())) && world.func_175623_d(p)) {
/* 102 */       Task t = TaskHandler.getTask(world.field_73011_w.getDimension(), ((ReplantInfo)this.replantTasks.get(Long.valueOf(p.func_177986_g()))).taskid);
/*     */       
/* 104 */       if (t == null) {
/* 105 */         Task tt = new Task(seal.getSealPos(), ((ReplantInfo)this.replantTasks.get(Long.valueOf(p.func_177986_g()))).pos);
/* 106 */         tt.setPriority(seal.getPriority());
/* 107 */         TaskHandler.addTask(world.field_73011_w.getDimension(), tt);
/* 108 */         ((ReplantInfo)this.replantTasks.get(Long.valueOf(p.func_177986_g()))).taskid = tt.getId();
/*     */       } 
/*     */     } 
/*     */   }
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
/*     */   public boolean onTaskCompletion(World world, IGolemAPI golem, Task task) {
/* 125 */     if (CropUtils.isGrownCrop(world, task.getPos())) {
/*     */       
/* 127 */       FakePlayer fp = FakePlayerFactory.get((WorldServer)world, new GameProfile((UUID)null, "FakeThaumcraftGolem"));
/* 128 */       fp.field_71135_a = new FakeNetHandlerPlayServer(fp.field_71133_b, new NetworkManager(EnumPacketDirection.CLIENTBOUND), fp);
/* 129 */       fp.func_70107_b((golem.getGolemEntity()).field_70165_t, (golem.getGolemEntity()).field_70163_u, (golem.getGolemEntity()).field_70161_v);
/*     */       
/* 131 */       EnumFacing face = EnumFacing.func_190914_a(task.getPos(), golem.getGolemEntity());
/* 132 */       IBlockState bs = world.func_180495_p(task.getPos());
/*     */       
/* 134 */       if (CropUtils.clickableCrops.contains(bs.func_177230_c().func_149739_a() + bs.func_177230_c().func_176201_c(bs))) {
/* 135 */         bs.func_177230_c().func_180639_a(world, task.getPos(), bs, fp, EnumHand.MAIN_HAND, face, 0.0F, 0.0F, 0.0F);
/* 136 */         golem.addRankXp(1);
/* 137 */         golem.swingArm();
/*     */       } else {
/* 139 */         GolemInteractionHelper.golemClick(world, golem, task.getPos(), (task.getSealPos()).face, ItemStack.field_190927_a, false, true);
/*     */ 
/*     */         
/* 142 */         if (CropUtils.isGrownCrop(world, task.getPos()))
/*     */         {
/* 144 */           BlockUtils.harvestBlock(world, fp, task.getPos(), true, false, 0, true);
/* 145 */           golem.addRankXp(1);
/* 146 */           golem.swingArm();
/* 147 */           if ((getToggles()[0]).value) {
/* 148 */             ItemStack seed = ThaumcraftApi.getSeed(bs.func_177230_c());
/* 149 */             if (seed != null && !seed.func_190926_b()) {
/* 150 */               IBlockState bb = world.func_180495_p(task.getPos().func_177977_b());
/* 151 */               EnumFacing rf = null;
/* 152 */               if (seed.func_77973_b() instanceof IPlantable && bb
/* 153 */                 .func_177230_c().canSustainPlant(bb, world, task.getPos().func_177977_b(), EnumFacing.UP, (IPlantable)seed
/* 154 */                   .func_77973_b())) {
/* 155 */                 rf = EnumFacing.DOWN;
/* 156 */               } else if (!(seed.func_77973_b() instanceof IPlantable) && 
/* 157 */                 bs.func_177230_c() instanceof BlockDirectional) {
/* 158 */                 rf = (EnumFacing)bs.func_177229_b(BlockDirectional.field_176387_N);
/*     */               } 
/*     */               
/* 161 */               if (rf != null) {
/* 162 */                 Task tt = new Task(task.getSealPos(), task.getPos());
/* 163 */                 tt.setPriority(task.getPriority());
/* 164 */                 tt.setLifespan((short)300);
/* 165 */                 this.replantTasks.put(Long.valueOf(tt.getPos().func_177986_g()), new ReplantInfo(tt.getPos(), rf, tt.getId(), seed
/* 166 */                       .func_77946_l(), bb.func_177230_c() instanceof net.minecraft.block.BlockFarmland));
/* 167 */                 TaskHandler.addTask(world.field_73011_w.getDimension(), tt);
/*     */               }
/*     */             
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 179 */     else if (this.replantTasks.containsKey(Long.valueOf(task.getPos().func_177986_g())) && ((ReplantInfo)this.replantTasks.get(Long.valueOf(task.getPos().func_177986_g()))).taskid == task.getId() && world
/* 180 */       .func_175623_d(task.getPos()) && golem.isCarrying(((ReplantInfo)this.replantTasks.get(Long.valueOf(task.getPos().func_177986_g()))).stack)) {
/* 181 */       FakePlayer fp = FakePlayerFactory.get((WorldServer)world, new GameProfile((UUID)null, "FakeThaumcraftGolem"));
/* 182 */       fp.func_70107_b((golem.getGolemEntity()).field_70165_t, (golem.getGolemEntity()).field_70163_u, (golem.getGolemEntity()).field_70161_v);
/* 183 */       IBlockState bb = world.func_180495_p(task.getPos().func_177977_b());
/* 184 */       ReplantInfo ri = (ReplantInfo)this.replantTasks.get(Long.valueOf(task.getPos().func_177986_g()));
/* 185 */       if ((bb.func_177230_c() instanceof net.minecraft.block.BlockDirt || bb.func_177230_c() instanceof net.minecraft.block.BlockGrass) && ri.farmland) {
/* 186 */         Items.field_151012_L.func_180614_a(fp, world, task.getPos().func_177977_b(), EnumHand.MAIN_HAND, EnumFacing.UP, 0.5F, 0.5F, 0.5F);
/*     */       }
/* 188 */       ItemStack seed = ri.stack.func_77946_l();
/* 189 */       seed.func_190920_e(1);
/* 190 */       if (seed.func_77973_b().func_180614_a(fp, world, task.getPos().func_177972_a(ri.face), EnumHand.MAIN_HAND, ri.face
/* 191 */           .func_176734_d(), 0.5F, 0.5F, 0.5F) == EnumActionResult.SUCCESS) {
/* 192 */         world.func_175669_a(2001, task.getPos(), Block.func_176210_f(world.func_180495_p(task.getPos())));
/* 193 */         golem.dropItem(seed);
/* 194 */         golem.addRankXp(1);
/* 195 */         golem.swingArm();
/*     */       } 
/*     */     } 
/*     */     
/* 199 */     task.setSuspended(true);
/* 200 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canGolemPerformTask(IGolemAPI golem, Task task) {
/* 205 */     if (this.replantTasks.containsKey(Long.valueOf(task.getPos().func_177986_g())) && ((ReplantInfo)this.replantTasks.get(Long.valueOf(task.getPos().func_177986_g()))).taskid == task.getId()) {
/* 206 */       boolean carry = golem.isCarrying(((ReplantInfo)this.replantTasks.get(Long.valueOf(task.getPos().func_177986_g()))).stack);
/* 207 */       if (!carry && (getToggles()[1]).value) {
/* 208 */         ISealEntity se = SealHandler.getSealEntity((golem.getGolemWorld()).field_73011_w.getDimension(), task.getSealPos());
/* 209 */         if (se != null) GolemHelper.requestProvisioning(golem.getGolemWorld(), se, ((ReplantInfo)this.replantTasks.get(Long.valueOf(task.getPos().func_177986_g()))).stack); 
/*     */       } 
/* 211 */       return carry;
/*     */     } 
/* 213 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onTaskSuspension(World world, Task task) {}
/*     */ 
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbt) {
/* 222 */     NBTTagList nbttaglist = nbt.func_150295_c("replant", 10);
/* 223 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 225 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 226 */       long loc = nbttagcompound1.func_74763_f("taskloc");
/* 227 */       byte face = nbttagcompound1.func_74771_c("taskface");
/* 228 */       boolean farmland = nbttagcompound1.func_74767_n("farmland");
/* 229 */       ItemStack stack = new ItemStack(nbttagcompound1);
/* 230 */       this.replantTasks.put(Long.valueOf(loc), new ReplantInfo(BlockPos.func_177969_a(loc), EnumFacing.field_82609_l[face], 0, stack, farmland));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbt) {
/* 236 */     if ((getToggles()[0]).value) {
/* 237 */       NBTTagList nbttaglist = new NBTTagList();
/* 238 */       for (Long key : this.replantTasks.keySet()) {
/*     */         
/* 240 */         ReplantInfo info = (ReplantInfo)this.replantTasks.get(key);
/* 241 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 242 */         nbttagcompound1.func_74772_a("taskloc", info.pos.func_177986_g());
/* 243 */         nbttagcompound1.func_74774_a("taskface", (byte)info.face.ordinal());
/* 244 */         nbttagcompound1.func_74757_a("farmland", info.farmland);
/* 245 */         info.stack.func_77955_b(nbttagcompound1);
/* 246 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       } 
/* 248 */       nbt.func_74782_a("replant", nbttaglist);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 254 */   public boolean canPlaceAt(World world, BlockPos pos, EnumFacing side) { return !world.func_175623_d(pos); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 259 */   public ResourceLocation getSealIcon() { return this.icon; }
/*     */ 
/*     */   
/* 262 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_harvest");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onRemoval(World world, BlockPos pos, EnumFacing side) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 272 */   public Object returnContainer(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal) { return new SealBaseContainer(player.field_71071_by, world, seal); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/* 278 */   public Object returnGui(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal) { return new SealBaseGUI(player.field_71071_by, world, seal); }
/*     */ 
/*     */ 
/*     */   
/* 282 */   public int[] getGuiCategories() { return new int[] { 2, 3, 0, 4 }; }
/*     */   
/* 284 */   protected ISealConfigToggles.SealToggle[] props = { new ISealConfigToggles.SealToggle(true, "prep", "golem.prop.replant"), new ISealConfigToggles.SealToggle(false, "ppro", "golem.prop.provision") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 291 */   public ISealConfigToggles.SealToggle[] getToggles() { return this.props; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 296 */   public void setToggle(int indx, boolean value) { this.props[indx].setValue(value); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 301 */   public EnumGolemTrait[] getRequiredTags() { return new EnumGolemTrait[] { EnumGolemTrait.DEFT, EnumGolemTrait.SMART }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 306 */   public EnumGolemTrait[] getForbiddenTags() { return null; }
/*     */   
/*     */   private class ReplantInfo
/*     */   {
/*     */     EnumFacing face;
/*     */     BlockPos pos;
/*     */     int taskid;
/*     */     ItemStack stack;
/*     */     boolean farmland;
/*     */     
/*     */     public ReplantInfo(BlockPos pos, EnumFacing face, int taskid, ItemStack stack, boolean farmland) {
/* 317 */       this.pos = pos;
/* 318 */       this.face = face;
/* 319 */       this.taskid = taskid;
/* 320 */       this.stack = stack;
/* 321 */       this.farmland = farmland;
/*     */     }
/*     */   }
/*     */   
/*     */   public void onTaskStarted(World world, IGolemAPI golem, Task task) {}
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\seals\SealHarvest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */