/*     */ package thaumcraft.common.golems.seals;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.EnumPacketDirection;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.util.FakePlayer;
/*     */ import net.minecraftforge.common.util.FakePlayerFactory;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.GolemHelper;
/*     */ import thaumcraft.api.golems.IGolemAPI;
/*     */ import thaumcraft.api.golems.seals.ISealConfigArea;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.common.golems.client.gui.SealBaseContainer;
/*     */ import thaumcraft.common.golems.client.gui.SealBaseGUI;
/*     */ import thaumcraft.common.golems.tasks.TaskHandler;
/*     */ import thaumcraft.common.lib.network.FakeNetHandlerPlayServer;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SealBreaker
/*     */   extends SealFiltered
/*     */   implements ISealConfigArea, ISealConfigToggles
/*     */ {
/*  44 */   public String getKey() { return "thaumcraft:breaker"; }
/*     */ 
/*     */   
/*  47 */   int delay = (new Random(System.nanoTime())).nextInt(42);
/*     */   
/*  49 */   HashMap<Integer, Long> cache = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickSeal(World world, ISealEntity seal) {
/*  55 */     if (this.delay % 100 == 0) {
/*  56 */       Iterator<Integer> it = this.cache.keySet().iterator();
/*  57 */       while (it.hasNext()) {
/*  58 */         Task t = TaskHandler.getTask(world.field_73011_w.getDimension(), ((Integer)it.next()).intValue());
/*  59 */         if (t == null) {
/*  60 */           it.remove();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  65 */     this.delay++;
/*     */     
/*  67 */     BlockPos p = GolemHelper.getPosInArea(seal, this.delay);
/*     */     
/*  69 */     if (!this.cache.containsValue(Long.valueOf(p.func_177986_g())) && isValidBlock(world, p)) {
/*  70 */       Task task = new Task(seal.getSealPos(), p);
/*  71 */       task.setPriority(seal.getPriority());
/*  72 */       task.setData((int)(world.func_180495_p(p).func_185887_b(world, p) * 10.0F));
/*  73 */       TaskHandler.addTask(world.field_73011_w.getDimension(), task);
/*  74 */       this.cache.put(Integer.valueOf(task.getId()), Long.valueOf(p.func_177986_g()));
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isValidBlock(World world, BlockPos p) {
/*  79 */     IBlockState bs = world.func_180495_p(p);
/*  80 */     if (!world.func_175623_d(p) && bs.func_185887_b(world, p) >= 0.0F) {
/*     */       
/*  82 */       for (ItemStack ts : getInv()) {
/*     */         
/*  84 */         if (ts != null && !ts.func_190926_b()) {
/*  85 */           ItemStack fs = BlockUtils.getSilkTouchDrop(bs);
/*  86 */           if (fs == null || !fs.func_190926_b()) {
/*  87 */             fs = new ItemStack(bs.func_177230_c(), 1, !(getToggles()[0]).value ? 32767 : bs.func_177230_c().func_176201_c(bs));
/*     */           }
/*  89 */           if (!(getToggles()[0]).value) fs.func_77964_b(32767);
/*     */           
/*  91 */           if (isBlacklist()) {
/*  92 */             if (OreDictionary.itemMatches(fs, ts, (getToggles()[0]).value)) return false;  continue;
/*     */           } 
/*  94 */           if (!OreDictionary.itemMatches(fs, ts, (getToggles()[0]).value)) return false;
/*     */         
/*     */         } 
/*     */       } 
/*     */       
/*  99 */       return true;
/*     */     } 
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onTaskCompletion(World world, IGolemAPI golem, Task task) {
/* 107 */     IBlockState bs = world.func_180495_p(task.getPos());
/* 108 */     if (this.cache.containsKey(Integer.valueOf(task.getId())) && isValidBlock(world, task.getPos())) {
/* 109 */       FakePlayer fp = FakePlayerFactory.get((WorldServer)world, new GameProfile((UUID)null, "FakeThaumcraftGolem"));
/* 110 */       fp.field_71135_a = new FakeNetHandlerPlayServer(fp.field_71133_b, new NetworkManager(EnumPacketDirection.CLIENTBOUND), fp);
/* 111 */       fp.func_70107_b((golem.getGolemEntity()).field_70165_t, (golem.getGolemEntity()).field_70163_u, (golem.getGolemEntity()).field_70161_v);
/* 112 */       golem.swingArm();
/*     */       
/* 114 */       boolean silky = (getToggles().length > 1 && (getToggles()[1]).value);
/* 115 */       int bspd = silky ? 7 : 21;
/* 116 */       if (task.getData() > bspd) {
/* 117 */         float bh = bs.func_185887_b(world, task.getPos()) * 10.0F;
/* 118 */         task.setLifespan((short)(int)Math.max(task.getLifespan(), 10L));
/* 119 */         task.setData(task.getData() - bspd);
/* 120 */         int progress = (int)(9.0F * (1.0F - task.getData() / bh));
/* 121 */         world.func_184133_a(null, task.getPos(), bs.func_177230_c().func_185467_w().func_185845_c(), SoundCategory.BLOCKS, (bs
/* 122 */             .func_177230_c().func_185467_w().func_185843_a() + 0.7F) / 8.0F, bs.func_177230_c().func_185467_w().func_185847_b() * 0.5F);
/* 123 */         BlockUtils.destroyBlockPartially(world, golem.getGolemEntity().func_145782_y(), task.getPos(), progress);
/* 124 */         return false;
/*     */       } 
/* 126 */       BlockUtils.destroyBlockPartially(world, golem.getGolemEntity().func_145782_y(), task.getPos(), 10);
/*     */ 
/*     */       
/* 129 */       BlockUtils.harvestBlock(world, fp, task.getPos(), true, silky, 0, true);
/* 130 */       golem.addRankXp(1);
/*     */       
/* 132 */       this.cache.remove(Integer.valueOf(task.getId()));
/*     */     } 
/* 134 */     task.setSuspended(true);
/* 135 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canGolemPerformTask(IGolemAPI golem, Task task) {
/* 140 */     if (this.cache.containsKey(Integer.valueOf(task.getId())) && isValidBlock(golem.getGolemWorld(), task.getPos())) return true; 
/* 141 */     task.setSuspended(true);
/* 142 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 147 */   public void onTaskSuspension(World world, Task task) { this.cache.remove(Integer.valueOf(task.getId())); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   public boolean canPlaceAt(World world, BlockPos pos, EnumFacing side) { return !world.func_175623_d(pos); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 157 */   public ResourceLocation getSealIcon() { return this.icon; }
/*     */ 
/*     */   
/* 160 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_breaker");
/*     */ 
/*     */ 
/*     */   
/*     */   public void onRemoval(World world, BlockPos pos, EnumFacing side) {}
/*     */ 
/*     */   
/* 167 */   public Object returnContainer(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal) { return new SealBaseContainer(player.field_71071_by, world, seal); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/* 173 */   public Object returnGui(World world, EntityPlayer player, BlockPos pos, EnumFacing side, ISealEntity seal) { return new SealBaseGUI(player.field_71071_by, world, seal); }
/*     */ 
/*     */ 
/*     */   
/* 177 */   public int[] getGuiCategories() { return new int[] { 2, 1, 3, 0, 4 }; }
/*     */ 
/*     */ 
/*     */   
/* 181 */   public EnumGolemTrait[] getRequiredTags() { return new EnumGolemTrait[] { EnumGolemTrait.BREAKER }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public EnumGolemTrait[] getForbiddenTags() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onTaskStarted(World world, IGolemAPI golem, Task task) {}
/*     */ 
/*     */ 
/*     */   
/* 195 */   public ISealConfigToggles.SealToggle[] getToggles() { return this.props; }
/*     */ 
/*     */   
/* 198 */   protected ISealConfigToggles.SealToggle[] props = { new ISealConfigToggles.SealToggle(true, "pmeta", "golem.prop.meta") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public void setToggle(int indx, boolean value) { this.props[indx].setValue(value); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\seals\SealBreaker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */