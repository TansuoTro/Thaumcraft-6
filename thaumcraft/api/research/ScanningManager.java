/*     */ package thaumcraft.api.research;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.TextComponentString;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.ThaumcraftInvHelper;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ 
/*     */ 
/*     */ public class ScanningManager
/*     */ {
/*  24 */   static ArrayList<IScanThing> things = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   public static void addScannableThing(IScanThing obj) { things.add(obj); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void scanTheThing(EntityPlayer player, Object object) {
/*  41 */     boolean found = false;
/*  42 */     boolean suppress = false;
/*  43 */     for (IScanThing thing : things) {
/*  44 */       if (thing.checkThing(player, object) && (
/*  45 */         thing.getResearchKey(player, object) == null || thing.getResearchKey(player, object).isEmpty() || ThaumcraftApi.internalMethods
/*  46 */         .progressResearch(player, thing.getResearchKey(player, object)))) {
/*  47 */         if (thing.getResearchKey(player, object) == null || thing.getResearchKey(player, object).isEmpty())
/*  48 */           suppress = true; 
/*  49 */         found = true;
/*  50 */         thing.onSuccess(player, object);
/*     */       } 
/*     */     } 
/*     */     
/*  54 */     if (!suppress) {
/*  55 */       if (!found) {
/*  56 */         player.func_146105_b(new TextComponentString("§5§o" + I18n.func_74838_a("tc.unknownobject")), true);
/*     */       } else {
/*  58 */         player.func_146105_b(new TextComponentString("§a§o" + I18n.func_74838_a("tc.knownobject")), true);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*  63 */     if (object instanceof BlockPos) {
/*  64 */       IItemHandler handler = ThaumcraftInvHelper.getItemHandlerAt(player.func_130014_f_(), (BlockPos)object, EnumFacing.UP);
/*  65 */       if (handler != null) {
/*  66 */         int scanned = 0;
/*  67 */         for (int slot = 0; slot < handler.getSlots(); slot++) {
/*  68 */           ItemStack stack = handler.getStackInSlot(slot);
/*  69 */           if (stack != null && !stack.func_190926_b()) {
/*  70 */             scanTheThing(player, stack);
/*  71 */             scanned++;
/*     */           } 
/*  73 */           if (scanned >= 100) {
/*  74 */             player.func_146105_b(new TextComponentString("§5§o" + I18n.func_74838_a("tc.invtoolarge")), true);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isThingStillScannable(EntityPlayer player, Object object) {
/*  90 */     for (IScanThing thing : things) {
/*  91 */       if (thing.checkThing(player, object)) {
/*     */         try {
/*  93 */           if (!ThaumcraftCapabilities.knowsResearch(player, new String[] { thing.getResearchKey(player, object) })) {
/*  94 */             return true;
/*     */           }
/*  96 */         } catch (Exception exception) {}
/*     */       }
/*     */     } 
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack getItemFromParms(EntityPlayer player, Object obj) {
/* 104 */     ItemStack is = ItemStack.field_190927_a;
/* 105 */     if (obj instanceof ItemStack)
/* 106 */       is = (ItemStack)obj; 
/* 107 */     if (obj instanceof EntityItem && ((EntityItem)obj).func_92059_d() != null)
/* 108 */       is = ((EntityItem)obj).func_92059_d(); 
/* 109 */     if (obj instanceof BlockPos) {
/* 110 */       IBlockState state = player.field_70170_p.func_180495_p((BlockPos)obj);
/* 111 */       is = state.func_177230_c().func_185473_a(player.field_70170_p, (BlockPos)obj, state);
/*     */       try {
/* 113 */         if (is == null || is.func_190926_b()) is = state.func_177230_c().getPickBlock(state, rayTrace(player), player.field_70170_p, (BlockPos)obj, player); 
/* 114 */       } catch (Exception exception) {}
/*     */       
/*     */       try {
/* 117 */         if ((is == null || is.func_190926_b()) && state.func_185904_a() == Material.field_151586_h) {
/* 118 */           is = new ItemStack(Items.field_151131_as);
/*     */         }
/* 120 */         if ((is == null || is.func_190926_b()) && state.func_185904_a() == Material.field_151587_i) {
/* 121 */           is = new ItemStack(Items.field_151129_at);
/*     */         }
/* 123 */       } catch (Exception exception) {}
/*     */     } 
/* 125 */     return is;
/*     */   }
/*     */ 
/*     */   
/*     */   private static RayTraceResult rayTrace(EntityPlayer player) {
/* 130 */     Vec3d vec3d = player.func_174824_e(0.0F);
/* 131 */     Vec3d vec3d1 = player.func_70676_i(0.0F);
/* 132 */     Vec3d vec3d2 = vec3d.func_72441_c(vec3d1.field_72450_a * 4.0D, vec3d1.field_72448_b * 4.0D, vec3d1.field_72449_c * 4.0D);
/* 133 */     return player.field_70170_p.func_147447_a(vec3d, vec3d2, true, false, true);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\ScanningManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */