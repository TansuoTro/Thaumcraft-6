/*     */ package thaumcraft.common.items.casters;
/*     */ 
/*     */ import baubles.api.BaublesApi;
/*     */ import java.util.HashMap;
/*     */ import java.util.TreeMap;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.casters.FocusEngine;
/*     */ import thaumcraft.api.casters.FocusPackage;
/*     */ import thaumcraft.api.casters.ICaster;
/*     */ import thaumcraft.api.items.IVisDiscountGear;
/*     */ import thaumcraft.api.potions.PotionVisExhaust;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.potions.PotionInfectiousVisExhaust;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CasterManager
/*     */ {
/*     */   public static float getTotalVisDiscount(EntityPlayer player) {
/*  25 */     int total = 0;
/*  26 */     if (player == null) return 0.0F;
/*     */     
/*  28 */     IInventory baubles = BaublesApi.getBaubles(player);
/*  29 */     for (int a = 0; a < baubles.func_70302_i_(); a++) {
/*  30 */       if (baubles.func_70301_a(a) != null && baubles
/*  31 */         .func_70301_a(a).func_77973_b() instanceof IVisDiscountGear) {
/*  32 */         total += ((IVisDiscountGear)baubles.func_70301_a(a).func_77973_b())
/*  33 */           .getVisDiscount(baubles.func_70301_a(a), player);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  38 */     for (int a = 0; a < 4; a++) {
/*  39 */       if (((ItemStack)player.field_71071_by.field_70460_b.get(a)).func_77973_b() instanceof IVisDiscountGear) {
/*  40 */         total += ((IVisDiscountGear)((ItemStack)player.field_71071_by.field_70460_b.get(a)).func_77973_b())
/*  41 */           .getVisDiscount((ItemStack)player.field_71071_by.field_70460_b.get(a), player);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  46 */     if (player.func_70644_a(PotionVisExhaust.instance) || player
/*  47 */       .func_70644_a(PotionInfectiousVisExhaust.instance)) {
/*  48 */       int level1 = 0;
/*  49 */       int level2 = 0;
/*  50 */       if (player.func_70644_a(PotionVisExhaust.instance)) {
/*  51 */         level1 = player.func_70660_b(PotionVisExhaust.instance).func_76458_c();
/*     */       }
/*  53 */       if (player.func_70644_a(PotionInfectiousVisExhaust.instance)) {
/*  54 */         level2 = player.func_70660_b(PotionInfectiousVisExhaust.instance).func_76458_c();
/*     */       }
/*  56 */       total -= (Math.max(level1, level2) + 1) * 10;
/*     */     } 
/*     */     
/*  59 */     return total / 100.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean consumeVisFromInventory(EntityPlayer player, float cost) {
/*  64 */     for (int a = player.field_71071_by.field_70462_a.size() - 1; a >= 0; a--) {
/*  65 */       ItemStack item = (ItemStack)player.field_71071_by.field_70462_a.get(a);
/*  66 */       if (item.func_77973_b() instanceof ICaster) {
/*  67 */         boolean done = ((ICaster)item.func_77973_b()).consumeVis(item, player, cost, true, false);
/*  68 */         if (done) return true; 
/*     */       } 
/*     */     } 
/*  71 */     return false;
/*     */   }
/*     */   
/*     */   public static void changeFocus(ItemStack is, World w, EntityPlayer player, String focus) {
/*  75 */     ICaster wand = (ICaster)is.func_77973_b();
/*  76 */     TreeMap<String, Integer> foci = new TreeMap<String, Integer>();
/*  77 */     HashMap<Integer, Integer> pouches = new HashMap<Integer, Integer>();
/*  78 */     int pouchcount = 0;
/*  79 */     ItemStack item = ItemStack.field_190927_a;
/*     */ 
/*     */     
/*  82 */     IInventory baubles = BaublesApi.getBaubles(player);
/*  83 */     for (int a = 0; a < baubles.func_70302_i_(); a++) {
/*  84 */       if (baubles.func_70301_a(a).func_77973_b() instanceof ItemFocusPouch) {
/*  85 */         pouchcount++;
/*  86 */         item = baubles.func_70301_a(a);
/*  87 */         pouches.put(Integer.valueOf(pouchcount), Integer.valueOf(a - 4));
/*  88 */         NonNullList<ItemStack> inv = ((ItemFocusPouch)item.func_77973_b()).getInventory(item);
/*  89 */         for (int q = 0; q < inv.size(); q++) {
/*  90 */           item = (ItemStack)inv.get(q);
/*  91 */           if (item.func_77973_b() instanceof ItemFocus) {
/*  92 */             String sh = ((ItemFocus)item.func_77973_b()).getSortingHelper(item);
/*  93 */             if (sh != null) {
/*  94 */               foci.put(sh, Integer.valueOf(q + pouchcount * 1000));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 101 */     for (int a = 0; a < 36; a++) {
/* 102 */       item = (ItemStack)player.field_71071_by.field_70462_a.get(a);
/* 103 */       if (item.func_77973_b() instanceof ItemFocus) {
/* 104 */         String sh = ((ItemFocus)item.func_77973_b()).getSortingHelper(item);
/* 105 */         if (sh == null)
/* 106 */           continue;  foci.put(sh, Integer.valueOf(a));
/*     */       } 
/* 108 */       if (item.func_77973_b() instanceof ItemFocusPouch) {
/* 109 */         pouchcount++;
/* 110 */         pouches.put(Integer.valueOf(pouchcount), Integer.valueOf(a));
/* 111 */         NonNullList<ItemStack> inv = ((ItemFocusPouch)item.func_77973_b()).getInventory(item);
/* 112 */         for (int q = 0; q < inv.size(); q++) {
/* 113 */           item = (ItemStack)inv.get(q);
/* 114 */           if (item.func_77973_b() instanceof ItemFocus) {
/* 115 */             String sh = ((ItemFocus)item.func_77973_b()).getSortingHelper(item);
/* 116 */             if (sh != null)
/* 117 */               foci.put(sh, Integer.valueOf(q + pouchcount * 1000)); 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       continue;
/*     */     } 
/* 123 */     if (focus.equals("REMOVE") || foci.size() == 0) {
/* 124 */       if (wand.getFocus(is) != null && (
/* 125 */         addFocusToPouch(player, wand.getFocusStack(is).func_77946_l(), pouches) || player.field_71071_by
/* 126 */         .func_70441_a(wand.getFocusStack(is).func_77946_l()))) {
/* 127 */         wand.setFocus(is, null);
/* 128 */         player.func_184185_a(SoundsTC.ticks, 0.3F, 0.9F);
/*     */       } 
/*     */       return;
/*     */     } 
/* 132 */     if (foci != null && foci.size() > 0 && focus != null) {
/*     */ 
/*     */       
/* 135 */       String newkey = focus;
/* 136 */       if (foci.get(newkey) == null) newkey = (String)foci.higherKey(newkey); 
/* 137 */       if (newkey == null || foci.get(newkey) == null) newkey = (String)foci.firstKey();
/*     */       
/* 139 */       if (((Integer)foci.get(newkey)).intValue() < 1000 && ((Integer)foci.get(newkey)).intValue() >= 0) {
/* 140 */         item = ((ItemStack)player.field_71071_by.field_70462_a.get(((Integer)foci.get(newkey)).intValue())).func_77946_l();
/*     */       } else {
/* 142 */         int pid = ((Integer)foci.get(newkey)).intValue() / 1000;
/* 143 */         if (pouches.containsKey(Integer.valueOf(pid))) {
/* 144 */           int pouchslot = ((Integer)pouches.get(Integer.valueOf(pid))).intValue();
/* 145 */           int focusslot = ((Integer)foci.get(newkey)).intValue() - pid * 1000;
/* 146 */           ItemStack tmp = ItemStack.field_190927_a;
/* 147 */           if (pouchslot >= 0) {
/* 148 */             tmp = ((ItemStack)player.field_71071_by.field_70462_a.get(pouchslot)).func_77946_l();
/*     */           } else {
/* 150 */             tmp = baubles.func_70301_a(pouchslot + 4).func_77946_l();
/*     */           } 
/* 152 */           item = fetchFocusFromPouch(player, focusslot, tmp, pouchslot);
/*     */         } 
/*     */       } 
/*     */       
/* 156 */       if (item != null && !item.func_190926_b()) {
/* 157 */         if (((Integer)foci.get(newkey)).intValue() < 1000 && ((Integer)foci.get(newkey)).intValue() >= 0) {
/* 158 */           player.field_71071_by.func_70299_a(((Integer)foci.get(newkey)).intValue(), ItemStack.field_190927_a);
/*     */         }
/*     */       } else {
/*     */         return;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 166 */       player.func_184185_a(SoundsTC.ticks, 0.3F, 1.0F);
/*     */ 
/*     */       
/* 169 */       if (wand.getFocus(is) != null && (
/* 170 */         addFocusToPouch(player, wand.getFocusStack(is).func_77946_l(), pouches) || player.field_71071_by
/* 171 */         .func_70441_a(wand.getFocusStack(is).func_77946_l()))) {
/* 172 */         wand.setFocus(is, ItemStack.field_190927_a);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 177 */       if (wand.getFocus(is) == null) {
/* 178 */         wand.setFocus(is, item);
/*     */       }
/* 180 */       else if (!addFocusToPouch(player, item, pouches)) {
/* 181 */         player.field_71071_by.func_70441_a(item);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static ItemStack fetchFocusFromPouch(EntityPlayer player, int focusid, ItemStack pouch, int pouchslot) {
/* 189 */     ItemStack focus = ItemStack.field_190927_a;
/* 190 */     NonNullList<ItemStack> inv = ((ItemFocusPouch)pouch.func_77973_b()).getInventory(pouch);
/* 191 */     ItemStack contents = (ItemStack)inv.get(focusid);
/* 192 */     if (contents.func_77973_b() instanceof ItemFocus) {
/* 193 */       focus = contents.func_77946_l();
/* 194 */       inv.set(focusid, ItemStack.field_190927_a);
/* 195 */       ((ItemFocusPouch)pouch.func_77973_b()).setInventory(pouch, inv);
/* 196 */       if (pouchslot >= 0) {
/* 197 */         player.field_71071_by.func_70299_a(pouchslot, pouch);
/* 198 */         player.field_71071_by.func_70296_d();
/*     */       } else {
/* 200 */         IInventory baubles = BaublesApi.getBaubles(player);
/* 201 */         baubles.func_70299_a(pouchslot + 4, pouch);
/* 202 */         BaublesApi.getBaublesHandler(player).setChanged(pouchslot + 4, true);
/* 203 */         baubles.func_70296_d();
/*     */       } 
/*     */     } 
/* 206 */     return focus;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean addFocusToPouch(EntityPlayer player, ItemStack focus, HashMap<Integer, Integer> pouches) {
/* 211 */     IInventory baubles = BaublesApi.getBaubles(player);
/* 212 */     for (Integer pouchslot : pouches.values()) {
/* 213 */       ItemStack pouch; if (pouchslot.intValue() >= 0) {
/* 214 */         pouch = (ItemStack)player.field_71071_by.field_70462_a.get(pouchslot.intValue());
/*     */       } else {
/* 216 */         pouch = baubles.func_70301_a(pouchslot.intValue() + 4);
/*     */       } 
/* 218 */       NonNullList<ItemStack> inv = ((ItemFocusPouch)pouch.func_77973_b()).getInventory(pouch);
/*     */       
/* 220 */       for (int q = 0; q < inv.size(); q++) {
/* 221 */         ItemStack contents = (ItemStack)inv.get(q);
/* 222 */         if (contents.func_190926_b()) {
/* 223 */           inv.set(q, focus.func_77946_l());
/* 224 */           ((ItemFocusPouch)pouch.func_77973_b()).setInventory(pouch, inv);
/* 225 */           if (pouchslot.intValue() >= 0) {
/* 226 */             player.field_71071_by.func_70299_a(pouchslot.intValue(), pouch);
/* 227 */             player.field_71071_by.func_70296_d();
/*     */           } else {
/* 229 */             baubles.func_70299_a(pouchslot.intValue() + 4, pouch);
/* 230 */             BaublesApi.getBaublesHandler(player).setChanged(pouchslot.intValue() + 4, true);
/* 231 */             baubles.func_70296_d();
/*     */           } 
/* 233 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 237 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void toggleMisc(ItemStack itemstack, World world, EntityPlayer player, int mod) {
/* 243 */     if (!(itemstack.func_77973_b() instanceof ICaster))
/* 244 */       return;  ICaster caster = (ICaster)itemstack.func_77973_b();
/* 245 */     ItemFocus focus = (ItemFocus)caster.getFocus(itemstack);
/* 246 */     FocusPackage fp = focus.getPackage(caster.getFocusStack(itemstack));
/*     */     
/* 248 */     if (fp != null && FocusEngine.doesPackageContainElement(fp, "thaumcraft.PLAN")) {
/* 249 */       int dim = getAreaDim(itemstack);
/*     */       
/* 251 */       if (mod == 0) {
/* 252 */         int areax = getAreaX(itemstack);
/* 253 */         int areay = getAreaY(itemstack);
/* 254 */         int areaz = getAreaZ(itemstack);
/* 255 */         int max = getAreaSize(itemstack);
/* 256 */         if (dim == 0) {
/* 257 */           areax++;
/* 258 */           areaz++;
/* 259 */           areay++;
/* 260 */         } else if (dim == 1) {
/* 261 */           areax++;
/* 262 */         } else if (dim == 2) {
/* 263 */           areaz++;
/* 264 */         } else if (dim == 3) {
/* 265 */           areay++;
/*     */         } 
/* 267 */         if (areax > max) {
/* 268 */           areax = 0;
/*     */         }
/* 270 */         if (areaz > max) {
/* 271 */           areaz = 0;
/*     */         }
/* 273 */         if (areay > max) {
/* 274 */           areay = 0;
/*     */         }
/* 276 */         setAreaX(itemstack, areax);
/* 277 */         setAreaY(itemstack, areay);
/* 278 */         setAreaZ(itemstack, areaz);
/*     */       } 
/*     */       
/* 281 */       if (mod == 1) {
/* 282 */         dim++;
/* 283 */         if (dim > 3) dim = 0; 
/* 284 */         setAreaDim(itemstack, dim);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getAreaSize(ItemStack itemstack) {
/* 293 */     boolean pot = false;
/* 294 */     if (!(itemstack.func_77973_b() instanceof ICaster)) return 0; 
/* 295 */     ICaster caster = (ICaster)itemstack.func_77973_b();
/* 296 */     ItemFocus focus = (ItemFocus)caster.getFocus(itemstack);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 303 */     return pot ? 6 : 3;
/*     */   }
/*     */   
/*     */   public static int getAreaDim(ItemStack stack) {
/* 307 */     if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("aread")) {
/* 308 */       return stack.func_77978_p().func_74762_e("aread");
/*     */     }
/* 310 */     return 0;
/*     */   }
/*     */   
/*     */   public static int getAreaX(ItemStack stack) {
/* 314 */     ICaster wand = (ICaster)stack.func_77973_b();
/* 315 */     if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("areax")) {
/* 316 */       int a = stack.func_77978_p().func_74762_e("areax");
/* 317 */       if (a > getAreaSize(stack)) {
/* 318 */         a = getAreaSize(stack);
/*     */       }
/* 320 */       return a;
/*     */     } 
/* 322 */     return getAreaSize(stack);
/*     */   }
/*     */   
/*     */   public static int getAreaY(ItemStack stack) {
/* 326 */     ICaster wand = (ICaster)stack.func_77973_b();
/* 327 */     if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("areay")) {
/* 328 */       int a = stack.func_77978_p().func_74762_e("areay");
/* 329 */       if (a > getAreaSize(stack)) {
/* 330 */         a = getAreaSize(stack);
/*     */       }
/* 332 */       return a;
/*     */     } 
/* 334 */     return getAreaSize(stack);
/*     */   }
/*     */   
/*     */   public static int getAreaZ(ItemStack stack) {
/* 338 */     ICaster wand = (ICaster)stack.func_77973_b();
/* 339 */     if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("areaz")) {
/* 340 */       int a = stack.func_77978_p().func_74762_e("areaz");
/* 341 */       if (a > getAreaSize(stack)) {
/* 342 */         a = getAreaSize(stack);
/*     */       }
/* 344 */       return a;
/*     */     } 
/* 346 */     return getAreaSize(stack);
/*     */   }
/*     */   
/*     */   public static void setAreaX(ItemStack stack, int area) {
/* 350 */     if (stack.func_77942_o()) {
/* 351 */       stack.func_77978_p().func_74768_a("areax", area);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setAreaY(ItemStack stack, int area) {
/* 356 */     if (stack.func_77942_o()) {
/* 357 */       stack.func_77978_p().func_74768_a("areay", area);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setAreaZ(ItemStack stack, int area) {
/* 362 */     if (stack.func_77942_o()) {
/* 363 */       stack.func_77978_p().func_74768_a("areaz", area);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setAreaDim(ItemStack stack, int dim) {
/* 368 */     if (stack.func_77942_o()) {
/* 369 */       stack.func_77978_p().func_74768_a("aread", dim);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 378 */   static HashMap<Integer, Long> cooldownServer = new HashMap();
/* 379 */   static HashMap<Integer, Long> cooldownClient = new HashMap();
/*     */   
/*     */   static boolean isOnCooldown(EntityLivingBase entityLiving) {
/* 382 */     if (entityLiving.field_70170_p.field_72995_K && cooldownClient.containsKey(Integer.valueOf(entityLiving.func_145782_y()))) {
/* 383 */       return (((Long)cooldownClient.get(Integer.valueOf(entityLiving.func_145782_y()))).longValue() > System.currentTimeMillis());
/*     */     }
/* 385 */     if (!entityLiving.field_70170_p.field_72995_K && cooldownServer.containsKey(Integer.valueOf(entityLiving.func_145782_y()))) {
/* 386 */       return (((Long)cooldownServer.get(Integer.valueOf(entityLiving.func_145782_y()))).longValue() > System.currentTimeMillis());
/*     */     }
/* 388 */     return false;
/*     */   }
/*     */   
/*     */   public static float getCooldown(EntityLivingBase entityLiving) {
/* 392 */     if (entityLiving.field_70170_p.field_72995_K && cooldownClient.containsKey(Integer.valueOf(entityLiving.func_145782_y()))) {
/* 393 */       return (float)(((Long)cooldownClient.get(Integer.valueOf(entityLiving.func_145782_y()))).longValue() - System.currentTimeMillis()) / 1000.0F;
/*     */     }
/* 395 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public static void setCooldown(EntityLivingBase entityLiving, int cd) {
/* 399 */     if (cd == 0) {
/* 400 */       cooldownClient.remove(Integer.valueOf(entityLiving.func_145782_y()));
/* 401 */       cooldownServer.remove(Integer.valueOf(entityLiving.func_145782_y()));
/*     */     }
/* 403 */     else if (entityLiving.field_70170_p.field_72995_K) {
/* 404 */       cooldownClient.put(Integer.valueOf(entityLiving.func_145782_y()), Long.valueOf(System.currentTimeMillis() + (cd * 50)));
/*     */     } else {
/* 406 */       cooldownServer.put(Integer.valueOf(entityLiving.func_145782_y()), Long.valueOf(System.currentTimeMillis() + (cd * 50)));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\casters\CasterManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */