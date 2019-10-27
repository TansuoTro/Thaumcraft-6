/*     */ package thaumcraft.common.items.casters;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.IItemPropertyGetter;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.casters.CasterTriggerRegistry;
/*     */ import thaumcraft.api.casters.FocusEngine;
/*     */ import thaumcraft.api.casters.FocusPackage;
/*     */ import thaumcraft.api.casters.ICaster;
/*     */ import thaumcraft.api.casters.IFocusElement;
/*     */ import thaumcraft.api.casters.IInteractWithCaster;
/*     */ import thaumcraft.api.items.IArchitect;
/*     */ import thaumcraft.codechicken.lib.math.MathHelper;
/*     */ import thaumcraft.common.items.ItemTCBase;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketAuraToClient;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.world.aura.AuraChunk;
/*     */ import thaumcraft.common.world.aura.AuraHandler;
/*     */ 
/*     */ 
/*     */ public class ItemCaster
/*     */   extends ItemTCBase
/*     */   implements IArchitect, ICaster
/*     */ {
/*  54 */   int area = 0;
/*     */   DecimalFormat myFormatter;
/*     */   ArrayList<BlockPos> checked;
/*     */   public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) { if (oldStack.func_77973_b() != null && oldStack.func_77973_b() == this && newStack.func_77973_b() != null && newStack.func_77973_b() == this) { ItemFocus oldf = ((ItemCaster)oldStack.func_77973_b()).getFocus(oldStack); ItemFocus newf = ((ItemCaster)newStack.func_77973_b()).getFocus(newStack); int s1 = 0; int s2 = 0; if (oldf != null && oldf.getSortingHelper(((ItemCaster)oldStack.func_77973_b()).getFocusStack(oldStack)) != null) s1 = oldf.getSortingHelper(((ItemCaster)oldStack.func_77973_b()).getFocusStack(oldStack)).hashCode();  if (newf != null && newf.getSortingHelper(((ItemCaster)newStack.func_77973_b()).getFocusStack(newStack)) != null) s2 = newf.getSortingHelper(((ItemCaster)newStack.func_77973_b()).getFocusStack(newStack)).hashCode();  return (s1 != s2); }  return (newStack.func_77973_b() != oldStack.func_77973_b()); } public boolean func_77645_m() { return false; } @SideOnly(Side.CLIENT) public boolean func_77662_d() { return true; } private float getAuraPool(EntityPlayer player) { tot = 0.0F; switch (this.area) { default: return AuraHandler.getVis(player.field_70170_p, player.func_180425_c());case 1: tot = AuraHandler.getVis(player.field_70170_p, player.func_180425_c()); for (EnumFacing face : EnumFacing.field_176754_o) tot += AuraHandler.getVis(player.field_70170_p, player.func_180425_c().func_177967_a(face, 16));  return tot;case 2: break; }  tot = 0.0F; for (int xx = -1; xx <= 1; xx++) { for (int zz = -1; zz <= 1; zz++) tot += AuraHandler.getVis(player.field_70170_p, player.func_180425_c().func_177982_a(xx * 16, 0, zz * 16));  }  return tot; } public boolean consumeVis(ItemStack is, EntityPlayer player, float amount, boolean crafting, boolean sim) { float i, i; amount *= getConsumptionModifier(is, player, crafting); float tot = getAuraPool(player); if (tot < amount) return false;  if (sim) return true;  switch (this.area) { default: amount -= AuraHandler.drainVis(player.field_70170_p, player.func_180425_c(), amount, sim); break;case 1: i = amount / 5.0F; while (amount > 0.0F) { if (i > amount) i = amount;  amount -= AuraHandler.drainVis(player.field_70170_p, player.func_180425_c(), i, sim); if (amount <= 0.0F) break;  if (i > amount) i = amount;  for (EnumFacing face : EnumFacing.field_176754_o) { amount -= AuraHandler.drainVis(player.field_70170_p, player.func_180425_c().func_177967_a(face, 16), i, sim); if (amount <= 0.0F) // Byte code: goto -> 309  }  }  break;case 2: i = amount / 9.0F; while (amount > 0.0F) { if (i > amount) i = amount;  for (int xx = -1; xx <= 1; xx++) { for (int zz = -1; zz <= 1; zz++) { amount -= AuraHandler.drainVis(player.field_70170_p, player.func_180425_c().func_177982_a(xx * 16, 0, zz * 16), i, sim); if (amount <= 0.0F)
/*  58 */                 // Byte code: goto -> 309  }  }  }  break; }  return (amount <= 0.0F); } public ItemCaster(String name, int area) { super(name, new String[0]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 217 */     this.myFormatter = new DecimalFormat("#######.#");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 482 */     this.checked = new ArrayList(); this.area = area; this.field_77777_bU = 1; func_77656_e(0); func_185043_a(new ResourceLocation("focus"), new IItemPropertyGetter() {
/*     */           @SideOnly(Side.CLIENT) public float func_185085_a(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) { ItemFocus f = ((ItemCaster)stack.func_77973_b()).getFocus(stack); if (stack.func_77973_b() instanceof ItemCaster && f != null) return 1.0F;  return 0.0F; }
/*     */         }); }
/*     */   public float getConsumptionModifier(ItemStack is, EntityPlayer player, boolean crafting) { float consumptionModifier = 1.0F; if (player != null) consumptionModifier -= CasterManager.getTotalVisDiscount(player);  return Math.max(consumptionModifier, 0.1F); }
/* 486 */   public ItemFocus getFocus(ItemStack stack) { if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("focus")) { NBTTagCompound nbt = stack.func_77978_p().func_74775_l("focus"); ItemStack fs = new ItemStack(nbt); if (fs != null && !fs.func_190926_b()) return (ItemFocus)fs.func_77973_b();  }  return null; } public ItemStack getFocusStack(ItemStack stack) { if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("focus")) { NBTTagCompound nbt = stack.func_77978_p().func_74775_l("focus"); return new ItemStack(nbt); }  return null; } public void setFocus(ItemStack stack, ItemStack focus) { if (focus == null || focus.func_190926_b()) { stack.func_77978_p().func_82580_o("focus"); } else { stack.func_77983_a("focus", focus.func_77955_b(new NBTTagCompound())); }  } public ArrayList<BlockPos> getArchitectBlocks(ItemStack stack, World world, BlockPos pos, EnumFacing side, EntityPlayer player) { ItemFocus focus = getFocus(stack);
/* 487 */     if (focus != null) {
/* 488 */       FocusPackage fp = focus.getPackage(getFocusStack(stack));
/* 489 */       if (fp != null) {
/* 490 */         for (IFocusElement fe : fp.nodes) {
/* 491 */           if (fe instanceof IArchitect) {
/* 492 */             return ((IArchitect)fe).getArchitectBlocks(stack, world, pos, side, player);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/* 497 */     return null; } public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.UNCOMMON; } @SideOnly(Side.CLIENT) public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) { if (stack.func_77942_o()) { String text = ""; ItemStack focus = getFocusStack(stack); if (focus != null && !focus.func_190926_b()) { float amt = ((ItemFocus)focus.func_77973_b()).getVisCost(focus); if (amt > 0.0F) text = "§r" + this.myFormatter.format(amt) + " " + I18n.func_74838_a("item.Focus.cost1");  }  tooltip.add(TextFormatting.ITALIC + "" + TextFormatting.AQUA + I18n.func_74838_a("tc.vis.cost") + " " + text); }  if (getFocus(stack) != null) { tooltip.add(TextFormatting.BOLD + "" + TextFormatting.ITALIC + "" + TextFormatting.GREEN + getFocus(stack).func_77653_i(getFocusStack(stack))); getFocus(stack).addFocusInformation(getFocusStack(stack), worldIn, tooltip, flagIn); }  } public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) { super.onArmorTick(world, player, itemStack); }
/*     */   public void func_77663_a(ItemStack is, World w, Entity e, int slot, boolean currentItem) { if (!w.field_72995_K && e.field_70173_aa % 10 == 0 && e instanceof EntityPlayerMP) for (ItemStack h : e.func_184214_aD()) { if (h != null && !h.func_190926_b() && h.func_77973_b() instanceof ICaster) { updateAura(is, w, (EntityPlayerMP)e); break; }  }   }
/*     */   private void updateAura(ItemStack stack, World world, EntityPlayerMP player) { int xx; AuraChunk ac, ac; float cv = 0.0F; float cf = 0.0F; short bv = 0; switch (this.area) { default: ac = AuraHandler.getAuraChunk(world.field_73011_w.getDimension(), (int)player.field_70165_t >> 4, (int)player.field_70161_v >> 4); if (ac == null)
/*     */           break;  cv = ac.getVis(); cf = ac.getFlux(); bv = ac.getBase(); break;case 1: ac = AuraHandler.getAuraChunk(world.field_73011_w.getDimension(), (int)player.field_70165_t >> 4, (int)player.field_70161_v >> 4); if (ac == null)
/*     */           break;  cv = ac.getVis(); cf = ac.getFlux(); bv = ac.getBase(); for (EnumFacing face : EnumFacing.field_176754_o) { ac = AuraHandler.getAuraChunk(world.field_73011_w.getDimension(), ((int)player.field_70165_t >> 4) + face.func_82601_c(), ((int)player.field_70161_v >> 4) + face.func_82599_e()); if (ac != null) { cv += ac.getVis(); cf += ac.getFlux(); bv = (short)(bv + ac.getBase()); }  }  break;case 2: for (xx = -1; xx <= 1; xx++) { for (int zz = -1; zz <= 1; zz++) { AuraChunk ac = AuraHandler.getAuraChunk(world.field_73011_w.getDimension(), ((int)player.field_70165_t >> 4) + xx, ((int)player.field_70161_v >> 4) + zz); if (ac != null) { cv += ac.getVis(); cf += ac.getFlux(); bv = (short)(bv + ac.getBase()); }  }  }  break; }  PacketHandler.INSTANCE.sendTo(new PacketAuraToClient(new AuraChunk(null, bv, cv, cf)), player); }
/* 502 */   public boolean showAxis(ItemStack stack, World world, EntityPlayer player, EnumFacing side, IArchitect.EnumAxis axis) { ItemFocus focus = getFocus(stack);
/* 503 */     if (focus != null) {
/* 504 */       FocusPackage fp = focus.getPackage(getFocusStack(stack));
/* 505 */       if (fp != null) {
/* 506 */         for (IFocusElement fe : fp.nodes) {
/* 507 */           if (fe instanceof IArchitect) {
/* 508 */             return ((IArchitect)fe).showAxis(stack, world, player, side, axis);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/* 513 */     return false; } public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) { IBlockState bs = world.func_180495_p(pos); if (bs.func_177230_c() instanceof IInteractWithCaster && ((IInteractWithCaster)bs.func_177230_c()).onCasterRightClick(world, player.func_184586_b(hand), player, pos, side, hand)) return EnumActionResult.PASS;  TileEntity tile = world.func_175625_s(pos); if (tile != null && tile instanceof IInteractWithCaster && ((IInteractWithCaster)tile).onCasterRightClick(world, player.func_184586_b(hand), player, pos, side, hand)) return EnumActionResult.PASS;  if (CasterTriggerRegistry.hasTrigger(bs)) return CasterTriggerRegistry.performTrigger(world, player.func_184586_b(hand), player, pos, side, bs) ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;  ItemStack fb = getFocusStack(player.func_184586_b(hand)); if (fb != null && !fb.func_190926_b()) { FocusPackage core = ItemFocus.getPackage(fb); for (IFocusElement fe : core.nodes) { if (fe instanceof thaumcraft.api.casters.IFocusBlockPicker && player.func_70093_af() && world.func_175625_s(pos) == null) { if (!world.field_72995_K) { ItemStack isout = new ItemStack(bs.func_177230_c(), 1, bs.func_177230_c().func_176201_c(bs)); try { if (bs != Blocks.field_150350_a) { ItemStack is = BlockUtils.getSilkTouchDrop(bs); if (is != null && !is.func_190926_b()) isout = is.func_77946_l();  }  } catch (Exception exception) {} storePickedBlock(player.func_184586_b(hand), isout); } else { player.func_184609_a(hand); return EnumActionResult.PASS; }  return EnumActionResult.SUCCESS; }  }  }  return EnumActionResult.PASS; } private RayTraceResult generateSourceVector(Entity e) { Vec3d v = e.func_174791_d(); boolean mainhand = true; if (e instanceof EntityPlayer) if (((EntityPlayer)e).func_184614_ca() != null && ((EntityPlayer)e).func_184614_ca().func_77973_b() instanceof ICaster) { mainhand = true; } else if (((EntityPlayer)e).func_184592_cb() != null && ((EntityPlayer)e).func_184592_cb().func_77973_b() instanceof ICaster) { mainhand = false; }   double posX = -MathHelper.cos(((e.field_70177_z - 0.5F) / 180.0F * 3.141593F)) * 0.20000000298023224D * (mainhand ? true : -1); double posZ = -MathHelper.sin(((e.field_70177_z - 0.5F) / 180.0F * 3.141593F)) * 0.30000001192092896D * (mainhand ? true : -1); Vec3d vl = e.func_70040_Z(); v = v.func_72441_c(posX, e.func_70047_e() - 0.4000000014901161D, posZ); v = v.func_178787_e(vl); return new RayTraceResult(e, v); } public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) { ItemStack focusStack = getFocusStack(player.func_184586_b(hand)); ItemFocus focus = getFocus(player.func_184586_b(hand)); if (focus != null && !CasterManager.isOnCooldown(player)) { CasterManager.setCooldown(player, focus.getActivationTime(focusStack)); FocusPackage core = ItemFocus.getPackage(focusStack); if (player.func_70093_af()) for (IFocusElement fe : core.nodes) { if (fe instanceof thaumcraft.api.casters.IFocusBlockPicker && player.func_70093_af())
/*     */             return new ActionResult(EnumActionResult.PASS, player.func_184586_b(hand));  }   if (world.field_72995_K)
/*     */         return new ActionResult(EnumActionResult.SUCCESS, player.func_184586_b(hand));  if (consumeVis(player.func_184586_b(hand), player, focus.getVisCost(focusStack), false, false)) { FocusEngine.castFocusPackage(player, core); player.func_184609_a(hand); return new ActionResult(EnumActionResult.SUCCESS, player.func_184586_b(hand)); }  return new ActionResult(EnumActionResult.FAIL, player.func_184586_b(hand)); }  return super.func_77659_a(world, player, hand); }
/*     */   public int func_77626_a(ItemStack itemstack) { return 72000; }
/*     */   public EnumAction func_77661_b(ItemStack stack1) { return EnumAction.BOW; }
/* 518 */   public RayTraceResult getArchitectMOP(ItemStack stack, World world, EntityLivingBase player) { ItemFocus focus = getFocus(stack);
/* 519 */     if (focus != null) {
/* 520 */       FocusPackage fp = focus.getPackage(getFocusStack(stack));
/* 521 */       if (fp != null && FocusEngine.doesPackageContainElement(fp, "thaumcraft.PLAN")) {
/* 522 */         return ((IArchitect)FocusEngine.getElement("thaumcraft.PLAN")).getArchitectMOP(getFocusStack(stack), world, player);
/*     */       }
/*     */     } 
/* 525 */     return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 535 */   public boolean useBlockHighlight(ItemStack stack) { return false; }
/*     */ 
/*     */   
/*     */   public void storePickedBlock(ItemStack stack, ItemStack stackout) {
/* 539 */     NBTTagCompound item = new NBTTagCompound();
/* 540 */     stack.func_77983_a("picked", stackout.func_77955_b(item));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickedBlock(ItemStack stack) {
/* 546 */     if (stack == null || stack.func_190926_b()) return ItemStack.field_190927_a; 
/* 547 */     ItemStack out = null;
/* 548 */     ItemFocus focus = getFocus(stack);
/* 549 */     if (focus != null && stack.func_77942_o() && stack.func_77978_p().func_74764_b("picked"))
/* 550 */     { FocusPackage fp = focus.getPackage(getFocusStack(stack));
/* 551 */       if (fp != null)
/* 552 */         for (IFocusElement fe : fp.nodes)
/* 553 */         { if (fe instanceof thaumcraft.api.casters.IFocusBlockPicker)
/* 554 */           { out = new ItemStack(Blocks.field_150350_a);
/*     */             try {
/* 556 */               out = new ItemStack(stack.func_77978_p().func_74775_l("picked")); break;
/* 557 */             } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 563 */             return out; }  }   }  return out;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\ItemCaster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */