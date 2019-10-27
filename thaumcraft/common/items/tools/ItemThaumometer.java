/*     */ package thaumcraft.common.items.tools;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.TextComponentString;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.research.ScanningManager;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.client.lib.events.RenderEventHandler;
/*     */ import thaumcraft.common.items.ItemTCBase;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketAuraToClient;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.world.aura.AuraChunk;
/*     */ import thaumcraft.common.world.aura.AuraHandler;
/*     */ 
/*     */ public class ItemThaumometer
/*     */   extends ItemTCBase
/*     */ {
/*     */   public ItemThaumometer() {
/*  36 */     super("thaumometer", new String[0]);
/*  37 */     func_77625_d(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.UNCOMMON; }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer p, EnumHand hand) {
/*  48 */     if (world.field_72995_K) {
/*  49 */       drawFX(world, p);
/*  50 */       p.field_70170_p.func_184134_a(p.field_70165_t, p.field_70163_u, p.field_70161_v, SoundsTC.scan, SoundCategory.PLAYERS, 0.5F, 1.0F, false);
/*     */     } else {
/*  52 */       doScan(world, p);
/*     */     } 
/*  54 */     return new ActionResult(EnumActionResult.SUCCESS, p.func_184586_b(hand));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77663_a(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
/*  59 */     boolean held = (isSelected || itemSlot == 0);
/*  60 */     if (held && !world.field_72995_K && entity.field_70173_aa % 20 == 0 && entity instanceof EntityPlayerMP) {
/*  61 */       updateAura(stack, world, (EntityPlayerMP)entity);
/*     */     }
/*     */     
/*  64 */     if (held && world.field_72995_K && entity.field_70173_aa % 5 == 0 && entity instanceof EntityPlayer) {
/*  65 */       Entity target = EntityUtils.getPointedEntity(world, entity, 1.0D, 16.0D, 5.0F, true);
/*  66 */       if (target != null && 
/*  67 */         ScanningManager.isThingStillScannable((EntityPlayer)entity, target)) {
/*  68 */         FXDispatcher.INSTANCE.scanHighlight(target);
/*     */       }
/*     */       
/*  71 */       RenderEventHandler.thaumTarget = target;
/*     */       
/*  73 */       RayTraceResult mop = getRayTraceResultFromPlayerWild(world, (EntityPlayer)entity, true);
/*  74 */       if (mop != null && mop.func_178782_a() != null && 
/*  75 */         ScanningManager.isThingStillScannable((EntityPlayer)entity, mop.func_178782_a())) {
/*  76 */         FXDispatcher.INSTANCE.scanHighlight(mop.func_178782_a());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected RayTraceResult getRayTraceResultFromPlayerWild(World worldIn, EntityPlayer playerIn, boolean useLiquids) {
/*  83 */     float f = playerIn.field_70127_C + playerIn.field_70125_A - playerIn.field_70127_C + worldIn.field_73012_v.nextInt(25) - worldIn.field_73012_v.nextInt(25);
/*  84 */     float f1 = playerIn.field_70126_B + playerIn.field_70177_z - playerIn.field_70126_B + worldIn.field_73012_v.nextInt(25) - worldIn.field_73012_v.nextInt(25);
/*  85 */     double d0 = playerIn.field_70169_q + playerIn.field_70165_t - playerIn.field_70169_q;
/*  86 */     double d1 = playerIn.field_70167_r + playerIn.field_70163_u - playerIn.field_70167_r + playerIn.func_70047_e();
/*  87 */     double d2 = playerIn.field_70166_s + playerIn.field_70161_v - playerIn.field_70166_s;
/*  88 */     Vec3d vec3 = new Vec3d(d0, d1, d2);
/*  89 */     float f2 = MathHelper.func_76134_b(-f1 * 0.017453292F - 3.1415927F);
/*  90 */     float f3 = MathHelper.func_76126_a(-f1 * 0.017453292F - 3.1415927F);
/*  91 */     float f4 = -MathHelper.func_76134_b(-f * 0.017453292F);
/*  92 */     float f5 = MathHelper.func_76126_a(-f * 0.017453292F);
/*  93 */     float f6 = f3 * f4;
/*  94 */     float f7 = f2 * f4;
/*  95 */     double d3 = 16.0D;
/*  96 */     Vec3d vec31 = vec3.func_72441_c(f6 * d3, f5 * d3, f7 * d3);
/*  97 */     return worldIn.func_147447_a(vec3, vec31, useLiquids, !useLiquids, false);
/*     */   }
/*     */   
/*     */   private void updateAura(ItemStack stack, World world, EntityPlayerMP player) {
/* 101 */     AuraChunk ac = AuraHandler.getAuraChunk(world.field_73011_w.getDimension(), player.func_180425_c().func_177958_n() >> 4, player.func_180425_c().func_177952_p() >> 4);
/* 102 */     if (ac != null) {
/* 103 */       if ((ac.getFlux() > ac.getVis() || ac.getFlux() > (ac.getBase() / 3)) && !ThaumcraftCapabilities.knowsResearch(player, new String[] { "FLUX" })) {
/* 104 */         ResearchManager.startResearchWithPopup(player, "FLUX");
/* 105 */         player.func_146105_b(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_74838_a("research.FLUX.warn")), true);
/*     */       } 
/* 107 */       PacketHandler.INSTANCE.sendTo(new PacketAuraToClient(ac), player);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawFX(World worldIn, EntityPlayer playerIn) {
/* 112 */     Entity target = EntityUtils.getPointedEntity(worldIn, playerIn, 1.0D, 9.0D, 0.0F, true);
/* 113 */     if (target != null) {
/* 114 */       for (int a = 0; a < 10; a++)
/* 115 */         FXDispatcher.INSTANCE.blockRunes(target.field_70165_t - 0.5D, target.field_70163_u + (target
/*     */             
/* 117 */             .func_70047_e() / 2.0F), target.field_70161_v - 0.5D, 0.3F + worldIn.field_73012_v
/*     */             
/* 119 */             .nextFloat() * 0.7F, 0.0F, 0.3F + worldIn.field_73012_v.nextFloat() * 0.7F, (int)(target.field_70131_O * 15.0F), 0.03F); 
/*     */     } else {
/* 121 */       RayTraceResult mop = func_77621_a(worldIn, playerIn, true);
/* 122 */       if (mop != null && mop.func_178782_a() != null)
/* 123 */         for (int a = 0; a < 10; a++) {
/* 124 */           FXDispatcher.INSTANCE.blockRunes(mop
/* 125 */               .func_178782_a().func_177958_n(), mop.func_178782_a().func_177956_o() + 0.25D, mop.func_178782_a().func_177952_p(), 0.3F + worldIn.field_73012_v
/* 126 */               .nextFloat() * 0.7F, 0.0F, 0.3F + worldIn.field_73012_v.nextFloat() * 0.7F, 15, 0.03F);
/*     */         } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void doScan(World worldIn, EntityPlayer playerIn) {
/* 132 */     if (!worldIn.field_72995_K) {
/* 133 */       Entity target = EntityUtils.getPointedEntity(worldIn, playerIn, 1.0D, 9.0D, 0.0F, true);
/* 134 */       if (target != null) {
/* 135 */         ScanningManager.scanTheThing(playerIn, target);
/*     */       } else {
/* 137 */         RayTraceResult mop = func_77621_a(worldIn, playerIn, true);
/* 138 */         if (mop != null && mop.func_178782_a() != null) {
/* 139 */           ScanningManager.scanTheThing(playerIn, mop.func_178782_a());
/*     */         } else {
/* 141 */           ScanningManager.scanTheThing(playerIn, null);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\tools\ItemThaumometer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */