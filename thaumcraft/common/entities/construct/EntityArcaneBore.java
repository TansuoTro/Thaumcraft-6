/*     */ package thaumcraft.common.entities.construct;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.BlockRailBase;
/*     */ import net.minecraft.block.BlockRailPowered;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Enchantments;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.EnumPacketDirection;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.util.FakePlayer;
/*     */ import net.minecraftforge.common.util.FakePlayerFactory;
/*     */ import net.minecraftforge.event.world.BlockEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.ThaumcraftInvHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*     */ import thaumcraft.common.lib.network.FakeNetHandlerPlayServer;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBoreDig;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.world.aura.AuraHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber
/*     */ public class EntityArcaneBore
/*     */   extends EntityOwnedConstruct
/*     */ {
/*  68 */   BlockPos digTarget = null;
/*  69 */   BlockPos digTargetPrev = null;
/*  70 */   float digCost = 0.25F;
/*  71 */   int paused = 100;
/*  72 */   int maxPause = 100;
/*  73 */   long soundDelay = 0L;
/*  74 */   Object beam1 = null;
/*  75 */   double beamLength = 0.0D;
/*     */   
/*     */   public EntityArcaneBore(World worldIn) {
/*  78 */     super(worldIn);
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
/* 364 */     this.breakCounter = 0;
/* 365 */     this.digDelay = 0;
/* 366 */     this.digDelayMax = 0;
/* 367 */     this.radInc = 0.0F;
/* 368 */     this.spiral = 0;
/* 369 */     this.currentRadius = 0.0F;
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
/*     */ 
/*     */     
/* 534 */     this.charge = 0.0F;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 650 */     this.clientDigging = false;
/*     */     func_70105_a(0.9F, 0.9F);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1) {
/* 656 */     if (par1 == 16) {
/*     */       
/* 658 */       this.clientDigging = true;
/*     */     
/*     */     }
/* 661 */     else if (par1 == 17) {
/*     */       
/* 663 */       this.clientDigging = false;
/*     */     }
/*     */     else {
/*     */       
/* 667 */       super.func_70103_a(par1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public EntityArcaneBore(World worldIn, BlockPos pos, EnumFacing facing) {
/*     */     this(worldIn);
/*     */     setFacing(facing);
/*     */     func_70080_a(pos.func_177958_n() + 0.5D, pos.func_177956_o(), pos.func_177952_p() + 0.5D, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   protected void func_110147_ax() {
/*     */     super.func_110147_ax();
/*     */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
/*     */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0D);
/*     */   }
/*     */   
/*     */   public void func_70071_h_() {
/*     */     super.func_70071_h_();
/*     */     if (!this.field_70170_p.field_72995_K) {
/*     */       this.field_70177_z = this.field_70759_as;
/*     */       if (this.field_70173_aa % 50 == 0)
/*     */         func_70691_i(1.0F); 
/*     */       if (this.field_70173_aa % 10 == 0 && getCharge() < 10.0F)
/*     */         rechargeVis(); 
/*     */       int k = MathHelper.func_76128_c(this.field_70165_t);
/*     */       int l = MathHelper.func_76128_c(this.field_70163_u);
/*     */       int i1 = MathHelper.func_76128_c(this.field_70161_v);
/*     */       if (BlockRailBase.func_176562_d(this.field_70170_p, new BlockPos(k, l - 1, i1)))
/*     */         l--; 
/*     */       BlockPos blockpos = new BlockPos(k, l, i1);
/*     */       IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos);
/*     */       if (BlockRailBase.func_176563_d(iblockstate)) {
/*     */         if (iblockstate.func_177230_c() == BlocksTC.activatorRail) {
/*     */           boolean ac = ((Boolean)iblockstate.func_177229_b(BlockRailPowered.field_176569_M)).booleanValue();
/*     */           setActive(!ac);
/*     */         } 
/*     */       } else if (!func_184218_aH()) {
/*     */         setActive(this.field_70170_p.func_175640_z((new BlockPos(this)).func_177977_b()));
/*     */       } 
/*     */       if (validInventory())
/*     */         try {
/*     */           func_184614_ca().func_77945_a(this.field_70170_p, this, 0, true);
/*     */         } catch (Exception exception) {} 
/*     */     } 
/*     */     if (!isActive()) {
/*     */       this.digTarget = null;
/*     */       func_70671_ap().func_75650_a(this.field_70165_t + getFacing().func_82601_c(), this.field_70163_u, this.field_70161_v + getFacing().func_82599_e(), 10.0F, 33.0F);
/*     */     } 
/*     */     if (this.digTarget != null && getCharge() >= this.digCost && !this.field_70170_p.field_72995_K) {
/*     */       func_70671_ap().func_75650_a(this.digTarget.func_177958_n() + 0.5D, this.digTarget.func_177956_o(), this.digTarget.func_177952_p() + 0.5D, 10.0F, 90.0F);
/*     */       if (this.digDelay-- <= 0 && dig()) {
/*     */         setCharge((byte)(int)(getCharge() - this.digCost));
/*     */         if (this.soundDelay < System.currentTimeMillis()) {
/*     */           this.soundDelay = System.currentTimeMillis() + 1200L + this.field_70170_p.field_73012_v.nextInt(100);
/*     */           func_184185_a(SoundsTC.rumble, 0.25F, 0.9F + this.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     if (!this.field_70170_p.field_72995_K && this.digTarget == null && isActive() && validInventory()) {
/*     */       findNextBlockToDig();
/*     */       if (this.digTarget != null) {
/*     */         this.field_70170_p.func_72960_a(this, (byte)16);
/*     */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXBoreDig(this.digTarget, this, this.digDelayMax), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.getDimension(), this.digTarget.func_177958_n(), this.digTarget.func_177956_o(), this.digTarget.func_177952_p(), 32.0D));
/*     */       } else {
/*     */         this.field_70170_p.func_72960_a(this, (byte)17);
/*     */         func_70671_ap().func_75650_a(this.field_70165_t + (getFacing().func_82601_c() * 2), this.field_70163_u + (getFacing().func_96559_d() * 2) + func_70047_e(), this.field_70161_v + (getFacing().func_82599_e() * 2), 10.0F, 33.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean validInventory() {
/*     */     boolean b = (func_184614_ca() != null && !func_184614_ca().func_190926_b() && (func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemPickaxe || func_184614_ca().func_77973_b().getToolClasses(func_184614_ca()).contains("pickaxe")));
/*     */     if (b && func_184614_ca().func_77952_i() + 1 >= func_184614_ca().func_77958_k())
/*     */       b = false; 
/*     */     return b;
/*     */   }
/*     */   
/*     */   public int getDigRadius() {
/*     */     int r = 0;
/*     */     if (func_184614_ca() != null && !func_184614_ca().func_190926_b() && (func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemPickaxe || func_184614_ca().func_77973_b().getToolClasses(func_184614_ca()).contains("pickaxe"))) {
/*     */       r = func_184614_ca().func_77973_b().func_77619_b() / 3;
/*     */       r += EnumInfusionEnchantment.getInfusionEnchantmentLevel(func_184614_ca(), EnumInfusionEnchantment.DESTRUCTIVE) * 2;
/*     */     } 
/*     */     return (r <= 1) ? 2 : r;
/*     */   }
/*     */   
/*     */   public int getDigDepth() {
/*     */     r = getDigRadius() * 8;
/*     */     return EnumInfusionEnchantment.getInfusionEnchantmentLevel(func_184614_ca(), EnumInfusionEnchantment.BURROWING) * 16;
/*     */   }
/*     */   
/*     */   public int getFortune() {
/*     */     int r = 0;
/*     */     if (validInventory()) {
/*     */       r = EnchantmentHelper.func_77506_a(Enchantments.field_185308_t, func_184614_ca());
/*     */       int r2 = EnumInfusionEnchantment.getInfusionEnchantmentLevel(func_184614_ca(), EnumInfusionEnchantment.SOUNDING);
/*     */       r = Math.max(r, r2);
/*     */     } 
/*     */     return r;
/*     */   }
/*     */   
/*     */   public int getDigSpeed(IBlockState blockState) {
/*     */     int speed = 0;
/*     */     if (validInventory()) {
/*     */       speed = (int)(speed + func_184614_ca().func_77973_b().func_150893_a(func_184614_ca(), blockState) / 2.0F);
/*     */       speed += EnchantmentHelper.func_77506_a(Enchantments.field_185305_q, func_184614_ca());
/*     */     } 
/*     */     return speed;
/*     */   }
/*     */   
/*     */   public int getRefining() {
/*     */     int refining = 0;
/*     */     if (func_184614_ca() != null && !func_184614_ca().func_190926_b())
/*     */       refining = EnumInfusionEnchantment.getInfusionEnchantmentLevel(func_184614_ca(), EnumInfusionEnchantment.REFINING); 
/*     */     return refining;
/*     */   }
/*     */   
/*     */   public boolean hasSilkTouch() {
/*     */     return (func_184614_ca() != null && !func_184614_ca().func_190926_b() && EnchantmentHelper.func_77506_a(Enchantments.field_185306_r, func_184614_ca()) > 0);
/*     */   }
/*     */   
/*     */   private boolean canSilkTouch(BlockPos pos, IBlockState state) { return (hasSilkTouch() && state.func_177230_c().canSilkHarvest(this.field_70170_p, pos, state, null)); }
/*     */   
/*     */   private static HashMap<Integer, ArrayList<ItemStack>> drops = new HashMap();
/*     */   int breakCounter;
/*     */   int digDelay;
/*     */   int digDelayMax;
/*     */   float radInc;
/*     */   public int spiral;
/*     */   public float currentRadius;
/*     */   private float charge;
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void harvestBlockEvent(BlockEvent.HarvestDropsEvent event) {
/*     */     if (event.getHarvester() != null && event.getHarvester().func_70005_c_().equals("FakeThaumcraftBore")) {
/*     */       ArrayList<ItemStack> droplist = new ArrayList<ItemStack>();
/*     */       if (drops.containsKey(Integer.valueOf((event.getHarvester()).field_70720_be)) && drops.get(Integer.valueOf((event.getHarvester()).field_70720_be)) != null)
/*     */         droplist = (ArrayList)drops.get(Integer.valueOf((event.getHarvester()).field_70720_be)); 
/*     */       for (ItemStack s : event.getDrops()) {
/*     */         if ((event.getHarvester()).field_70170_p.field_73012_v.nextFloat() <= event.getDropChance())
/*     */           droplist.add(s); 
/*     */       } 
/*     */       drops.put(Integer.valueOf((event.getHarvester()).field_70720_be), droplist);
/*     */       event.getDrops().clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean dig() {
/*     */     boolean b = false;
/*     */     if (this.digTarget != null && !this.field_70170_p.func_175623_d(this.digTarget)) {
/*     */       IBlockState digBs = this.field_70170_p.func_180495_p(this.digTarget);
/*     */       if (!digBs.func_177230_c().isAir(digBs, this.field_70170_p, this.digTarget)) {
/*     */         boolean silktouch = false;
/*     */         int fortune = getFortune();
/*     */         if (canSilkTouch(this.digTarget, digBs)) {
/*     */           silktouch = true;
/*     */           fortune = 0;
/*     */         } 
/*     */         FakePlayer fp = FakePlayerFactory.get((WorldServer)this.field_70170_p, new GameProfile((UUID)null, "FakeThaumcraftBore"));
/*     */         fp.field_71135_a = new FakeNetHandlerPlayServer(fp.field_71133_b, new NetworkManager(EnumPacketDirection.CLIENTBOUND), fp);
/*     */         fp.field_70720_be = func_145782_y();
/*     */         fp.field_71090_bL = 1;
/*     */         fp.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
/*     */         fp.func_184611_a(EnumHand.MAIN_HAND, func_184614_ca());
/*     */         if (BlockUtils.harvestBlock(func_130014_f_(), fp, this.digTarget, false, false, fortune, false)) {
/*     */           ArrayList<ItemStack> items = (ArrayList)drops.get(Integer.valueOf(func_145782_y()));
/*     */           if (items == null)
/*     */             items = new ArrayList<ItemStack>(); 
/*     */           List<EntityItem> targets = this.field_70170_p.func_72872_a(EntityItem.class, (new AxisAlignedBB(this.digTarget.func_177958_n(), this.digTarget.func_177956_o(), this.digTarget.func_177952_p(), (this.digTarget.func_177958_n() + 1), (this.digTarget.func_177956_o() + 1), (this.digTarget.func_177952_p() + 1))).func_72314_b(1.5D, 1.5D, 1.5D));
/*     */           if (targets.size() > 0)
/*     */             for (EntityItem e : targets) {
/*     */               items.add(e.func_92059_d().func_77946_l());
/*     */               e.func_70106_y();
/*     */             }  
/*     */           int refining = getRefining();
/*     */           if (items.size() > 0)
/*     */             for (ItemStack is : items) {
/*     */               ItemStack dropped = is.func_77946_l();
/*     */               if (!silktouch && refining > 0)
/*     */                 dropped = Utils.findSpecialMiningResult(is, (refining + 1) * 0.125F, this.field_70170_p.field_73012_v); 
/*     */               if (dropped != null && !dropped.func_190926_b()) {
/*     */                 boolean e = false;
/*     */                 for (EnumFacing f : EnumFacing.field_82609_l) {
/*     */                   BlockPos p = func_180425_c().func_177972_a(f);
/*     */                   IItemHandler inventory = ThaumcraftInvHelper.getItemHandlerAt(func_130014_f_(), p, f);
/*     */                   if (inventory != null) {
/*     */                     InventoryUtils.ejectStackAt(func_130014_f_(), func_180425_c(), f, dropped);
/*     */                     e = true;
/*     */                     break;
/*     */                   } 
/*     */                 } 
/*     */                 if (!e)
/*     */                   InventoryUtils.ejectStackAt(func_130014_f_(), func_180425_c(), getFacing().func_176734_d(), dropped); 
/*     */               } 
/*     */             }  
/*     */           this.breakCounter += fp.field_71090_bL;
/*     */           items.clear();
/*     */         } 
/*     */       } 
/*     */       if (func_184614_ca() != null && !func_184614_ca().func_190926_b()) {
/*     */         if (this.breakCounter >= 50) {
/*     */           this.breakCounter -= 50;
/*     */           func_184614_ca().func_77972_a(1, this);
/*     */         } 
/*     */         if (func_184614_ca().func_190916_E() <= 0)
/*     */           func_184611_a(func_184600_cs(), ItemStack.field_190927_a); 
/*     */       } else {
/*     */         this.breakCounter = 0;
/*     */       } 
/*     */       b = this.field_70170_p.func_175698_g(this.digTarget);
/*     */     } 
/*     */     this.digTarget = null;
/*     */     return b;
/*     */   }
/*     */   
/*     */   private void findNextBlockToDig() {
/*     */     if (this.digTargetPrev == null || func_174831_c(this.digTargetPrev) > ((getDigRadius() + 1) * (getDigRadius() + 1)))
/*     */       this.digTargetPrev = new BlockPos(this); 
/*     */     if (this.radInc == 0.0F)
/*     */       this.radInc = 1.0F; 
/*     */     int digRadius = getDigRadius();
/*     */     int digDepth = getDigDepth();
/*     */     int x = this.digTargetPrev.func_177958_n();
/*     */     int z = this.digTargetPrev.func_177952_p();
/*     */     int y = this.digTargetPrev.func_177956_o();
/*     */     int x2 = x + getFacing().func_82601_c() * digDepth;
/*     */     int y2 = y + getFacing().func_96559_d() * digDepth;
/*     */     int z2 = z + getFacing().func_82599_e() * digDepth;
/*     */     BlockPos end = new BlockPos(x2, y2, z2);
/*     */     RayTraceResult mop = this.field_70170_p.func_147447_a((new Vec3d(this.digTargetPrev)).func_72441_c(0.5D, 0.5D, 0.5D), (new Vec3d(end)).func_72441_c(0.5D, 0.5D, 0.5D), false, true, false);
/*     */     if (mop != null) {
/*     */       Vec3d digger = new Vec3d(this.field_70165_t + getFacing().func_82601_c(), this.field_70163_u + func_70047_e() + getFacing().func_96559_d(), this.field_70161_v + getFacing().func_82599_e());
/*     */       mop = this.field_70170_p.func_147447_a(digger, (new Vec3d(mop.func_178782_a())).func_72441_c(0.5D, 0.5D, 0.5D), false, true, false);
/*     */       if (mop != null) {
/*     */         IBlockState blockState = this.field_70170_p.func_180495_p(mop.func_178782_a());
/*     */         if (blockState.func_185887_b(this.field_70170_p, mop.func_178782_a()) > -1.0F && blockState.func_185890_d(this.field_70170_p, mop.func_178782_a()) != null) {
/*     */           this.digDelay = Math.max(10 - getDigSpeed(blockState), (int)(blockState.func_185887_b(this.field_70170_p, mop.func_178782_a()) * 2.0F) - getDigSpeed(blockState) * 2);
/*     */           if (this.digDelay < 1)
/*     */             this.digDelay = 1; 
/*     */           this.digDelayMax = this.digDelay;
/*     */           if (!mop.func_178782_a().equals(func_180425_c()) && !mop.func_178782_a().equals(func_180425_c().func_177977_b())) {
/*     */             this.digTarget = mop.func_178782_a();
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     while (x == this.digTargetPrev.func_177958_n() && z == this.digTargetPrev.func_177952_p() && y == this.digTargetPrev.func_177956_o()) {
/*     */       if (Math.abs(this.currentRadius) > digRadius)
/*     */         this.currentRadius = digRadius; 
/*     */       this.spiral = (int)(this.spiral + 3.0F + Math.max(0.0F, (10.0F - Math.abs(this.currentRadius)) * 2.0F));
/*     */       if (this.spiral >= 360) {
/*     */         this.spiral -= 360;
/*     */         this.currentRadius += this.radInc;
/*     */         if (this.currentRadius > digRadius || this.currentRadius < -digRadius)
/*     */           this.currentRadius = 0.0F; 
/*     */       } 
/*     */       Vec3d vsource = new Vec3d((int)this.field_70165_t + 0.5D + getFacing().func_82601_c(), this.field_70163_u + getFacing().func_96559_d() + func_70047_e(), (int)this.field_70161_v + 0.5D + getFacing().func_82599_e());
/*     */       Vec3d vtar = new Vec3d(0.0D, this.currentRadius, 0.0D);
/*     */       vtar = Utils.rotateAroundZ(vtar, this.spiral / 180.0F * 3.1415927F);
/*     */       vtar = Utils.rotateAroundY(vtar, 1.5707964F * getFacing().func_82601_c());
/*     */       vtar = Utils.rotateAroundX(vtar, 1.5707964F * getFacing().func_96559_d());
/*     */       Vec3d vres = vsource.func_72441_c(vtar.field_72450_a, vtar.field_72448_b, vtar.field_72449_c);
/*     */       x = MathHelper.func_76128_c(vres.field_72450_a);
/*     */       y = MathHelper.func_76128_c(vres.field_72448_b);
/*     */       z = MathHelper.func_76128_c(vres.field_72449_c);
/*     */     } 
/*     */     this.digTargetPrev = new BlockPos(x, y, z);
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float amount) {
/*     */     try {
/*     */       if (source.func_76346_g() != null && isOwner((EntityLivingBase)source.func_76346_g())) {
/*     */         EnumFacing f = EnumFacing.func_190914_a(func_180425_c(), (EntityLivingBase)source.func_76346_g());
/*     */         if (f != EnumFacing.DOWN)
/*     */           setFacing(f); 
/*     */         return false;
/*     */       } 
/*     */     } catch (Exception exception) {}
/*     */     this.field_70177_z = (float)(this.field_70177_z + func_70681_au().nextGaussian() * 45.0D);
/*     */     this.field_70125_A = (float)(this.field_70125_A + func_70681_au().nextGaussian() * 20.0D);
/*     */     return super.func_70097_a(source, amount);
/*     */   }
/*     */   
/*     */   protected void rechargeVis() { setCharge(getCharge() + AuraHandler.drainVis(this.field_70170_p, func_180425_c(), 10.0F, false)); }
/*     */   
/*     */   public boolean func_70104_M() { return true; }
/*     */   
/*     */   public boolean func_70067_L() { return true; }
/*     */   
/*     */   public void func_70645_a(DamageSource cause) {
/*     */     super.func_70645_a(cause);
/*     */     if (!this.field_70170_p.field_72995_K)
/*     */       dropStuff(); 
/*     */   }
/*     */   
/*     */   protected void dropStuff() {
/*     */     if (func_184614_ca() != null && !func_184614_ca().func_190926_b())
/*     */       func_70099_a(func_184614_ca(), 0.5F); 
/*     */   }
/*     */   
/*     */   protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
/*     */     if (player.func_184586_b(hand).func_77973_b() instanceof net.minecraft.item.ItemNameTag)
/*     */       return false; 
/*     */     if (!this.field_70170_p.field_72995_K && isOwner(player) && !this.field_70128_L) {
/*     */       if (player.func_70093_af()) {
/*     */         func_184185_a(SoundsTC.zap, 1.0F, 1.0F);
/*     */         dropStuff();
/*     */         func_70099_a(new ItemStack(ItemsTC.turretPlacer, 1, 2), 0.5F);
/*     */         func_70106_y();
/*     */         player.func_184609_a(hand);
/*     */       } else {
/*     */         player.openGui(Thaumcraft.instance, 14, this.field_70170_p, func_145782_y(), 0, 0);
/*     */       } 
/*     */       return true;
/*     */     } 
/*     */     return super.func_184645_a(player, hand);
/*     */   }
/*     */   
/*     */   public void func_70653_a(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_) {
/*     */     super.func_70653_a(p_70653_1_, p_70653_2_, p_70653_3_ / 10.0D, p_70653_5_ / 10.0D);
/*     */     if (this.field_70181_x > 0.1D)
/*     */       this.field_70181_x = 0.1D; 
/*     */   }
/*     */   
/*     */   private static final DataParameter<EnumFacing> FACING = EntityDataManager.func_187226_a(EntityArcaneBore.class, DataSerializers.field_187202_l);
/*     */   private static final DataParameter<Boolean> ACTIVE = EntityDataManager.func_187226_a(EntityArcaneBore.class, DataSerializers.field_187198_h);
/*     */   public boolean clientDigging;
/*     */   
/*     */   protected void func_70088_a() {
/*     */     super.func_70088_a();
/*     */     func_184212_Q().func_187214_a(FACING, EnumFacing.DOWN);
/*     */     this.field_70180_af.func_187214_a(ACTIVE, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public boolean isActive() { return ((Boolean)this.field_70180_af.func_187225_a(ACTIVE)).booleanValue(); }
/*     */   
/*     */   public void setActive(boolean attacking) { this.field_70180_af.func_187227_b(ACTIVE, Boolean.valueOf(attacking)); }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbt) {
/*     */     super.func_70037_a(nbt);
/*     */     setCharge(nbt.func_74760_g("charge"));
/*     */     setFacing(EnumFacing.field_82609_l[nbt.func_74771_c("faceing")]);
/*     */     setActive(nbt.func_74767_n("active"));
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbt) {
/*     */     super.func_70014_b(nbt);
/*     */     nbt.func_74776_a("charge", getCharge());
/*     */     nbt.func_74774_a("faceing", (byte)getFacing().func_176745_a());
/*     */     nbt.func_74757_a("active", isActive());
/*     */   }
/*     */   
/*     */   public EnumFacing getFacing() { return (EnumFacing)func_184212_Q().func_187225_a(FACING); }
/*     */   
/*     */   public void setFacing(EnumFacing face) { func_184212_Q().func_187227_b(FACING, face); }
/*     */   
/*     */   public float getCharge() { return this.charge; }
/*     */   
/*     */   public void setCharge(float c) { this.charge = c; }
/*     */   
/*     */   public void func_70091_d(MoverType mt, double x, double y, double z) { super.func_70091_d(mt, x / 5.0D, y, z / 5.0D); }
/*     */   
/*     */   public void func_174812_G() { func_70097_a(DamageSource.field_76380_i, 400.0F); }
/*     */   
/*     */   protected void func_70628_a(boolean p_70628_1_, int treasure) {
/*     */     float b = treasure * 0.15F;
/*     */     if (this.field_70146_Z.nextFloat() < 0.2F + b)
/*     */       func_70099_a(new ItemStack(ItemsTC.mind), 0.5F); 
/*     */     if (this.field_70146_Z.nextFloat() < 0.2F + b)
/*     */       func_70099_a(new ItemStack(ItemsTC.morphicResonator), 0.5F); 
/*     */     if (this.field_70146_Z.nextFloat() < 0.2F + b)
/*     */       func_70099_a(new ItemStack(BlocksTC.crystalAir), 0.5F); 
/*     */     if (this.field_70146_Z.nextFloat() < 0.2F + b)
/*     */       func_70099_a(new ItemStack(BlocksTC.crystalEarth), 0.5F); 
/*     */     if (this.field_70146_Z.nextFloat() < 0.5F + b)
/*     */       func_70099_a(new ItemStack(ItemsTC.mechanismSimple), 0.5F); 
/*     */     if (this.field_70146_Z.nextFloat() < 0.5F + b)
/*     */       func_70099_a(new ItemStack(ItemsTC.plate), 0.5F); 
/*     */     if (this.field_70146_Z.nextFloat() < 0.5F + b)
/*     */       func_70099_a(new ItemStack(BlocksTC.plankGreatwood), 0.5F); 
/*     */   }
/*     */   
/*     */   public int func_70646_bf() { return 10; }
/*     */   
/*     */   public Team func_96124_cp() {
/*     */     if (isOwned()) {
/*     */       EntityLivingBase entitylivingbase = getOwnerEntity();
/*     */       if (entitylivingbase != null)
/*     */         return entitylivingbase.func_96124_cp(); 
/*     */     } 
/*     */     return super.func_96124_cp();
/*     */   }
/*     */   
/*     */   public float func_70047_e() { return 0.8125F; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\construct\EntityArcaneBore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */