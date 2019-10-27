/*     */ package thaumcraft.common.lib.utils;
/*     */ 
/*     */ import baubles.api.BaublesApi;
/*     */ import baubles.api.cap.IBaublesItemHandler;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityOwnable;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.items.IGoggles;
/*     */ import thaumcraft.api.items.IRevealer;
/*     */ import thaumcraft.common.entities.EntitySpecialItem;
/*     */ import thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss;
/*     */ import thaumcraft.common.entities.monster.mods.ChampionModifier;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityUtils
/*     */ {
/*     */   public static boolean isFriendly(Entity source, Entity target) {
/*  43 */     if (source == null || target == null) return false; 
/*  44 */     if (source.func_145782_y() == target.func_145782_y()) return true; 
/*  45 */     if (source.func_184215_y(target)) return true; 
/*  46 */     if (source.func_184191_r(target)) return true; 
/*  47 */     if (target instanceof IEntityOwnable && ((IEntityOwnable)target).func_70902_q() != null && ((IEntityOwnable)target).func_70902_q().equals(source)) return true; 
/*     */     try {
/*  49 */       if (!(target.func_130014_f_()).field_72995_K && target instanceof EntityPlayer)
/*     */       {
/*  51 */         if (!FMLCommonHandler.instance().getMinecraftServerInstance().func_71219_W()) return true;  } 
/*  52 */     } catch (Exception exception) {}
/*  53 */     return false;
/*     */   }
/*     */   
/*     */   public static Vec3d posToHand(Entity e, EnumHand hand) {
/*  57 */     double px = e.field_70165_t;
/*  58 */     double py = (e.func_174813_aQ()).field_72338_b + (e.field_70131_O / 2.0F) + 0.25D;
/*  59 */     double pz = e.field_70161_v;
/*  60 */     float m = (hand == EnumHand.MAIN_HAND) ? 0.0F : 180.0F;
/*  61 */     px += (-MathHelper.func_76134_b((e.field_70177_z + m) / 180.0F * 3.141593F) * 0.3F);
/*  62 */     pz += (-MathHelper.func_76126_a((e.field_70177_z + m) / 180.0F * 3.141593F) * 0.3F);
/*  63 */     Vec3d vec3d = e.func_70676_i(1.0F);
/*  64 */     px += vec3d.field_72450_a * 0.3D;
/*  65 */     py += vec3d.field_72448_b * 0.3D;
/*  66 */     pz += vec3d.field_72449_c * 0.3D;
/*  67 */     return new Vec3d(px, py, pz);
/*     */   }
/*     */   
/*     */   public static boolean hasGoggles(Entity e) {
/*  71 */     if (!(e instanceof EntityPlayer)) return false; 
/*  72 */     EntityPlayer viewer = (EntityPlayer)e;
/*     */     
/*  74 */     if (viewer.func_184614_ca().func_77973_b() instanceof IGoggles && 
/*  75 */       showPopups(viewer.func_184614_ca(), viewer)) return true;
/*     */ 
/*     */     
/*  78 */     for (int a = 0; a < 4; a++) {
/*  79 */       if (((ItemStack)viewer.field_71071_by.field_70460_b.get(a)).func_77973_b() instanceof IGoggles && 
/*  80 */         showPopups((ItemStack)viewer.field_71071_by.field_70460_b.get(a), viewer)) return true;
/*     */     
/*     */     } 
/*     */     
/*  84 */     IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(viewer);
/*  85 */     for (int a = 0; a < baubles.getSlots(); a++) {
/*  86 */       if (baubles.getStackInSlot(a).func_77973_b() instanceof IGoggles && 
/*  87 */         showPopups(baubles.getStackInSlot(a), viewer)) return true;
/*     */     
/*     */     } 
/*     */     
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */   
/*  95 */   private static boolean showPopups(ItemStack stack, EntityPlayer player) { return ((IGoggles)stack.func_77973_b()).showIngamePopups(stack, player); }
/*     */ 
/*     */   
/*     */   public static boolean hasRevealer(Entity e) {
/*  99 */     if (!(e instanceof EntityPlayer)) return false; 
/* 100 */     EntityPlayer viewer = (EntityPlayer)e;
/*     */     
/* 102 */     if (viewer.func_184614_ca().func_77973_b() instanceof IRevealer && 
/* 103 */       reveals(viewer.func_184614_ca(), viewer)) return true;
/*     */ 
/*     */     
/* 106 */     if (viewer.func_184592_cb().func_77973_b() instanceof IRevealer && 
/* 107 */       reveals(viewer.func_184592_cb(), viewer)) return true;
/*     */ 
/*     */     
/* 110 */     for (int a = 0; a < 4; a++) {
/* 111 */       if (((ItemStack)viewer.field_71071_by.field_70460_b.get(a)).func_77973_b() instanceof IRevealer && 
/* 112 */         reveals((ItemStack)viewer.field_71071_by.field_70460_b.get(a), viewer)) return true;
/*     */     
/*     */     } 
/*     */     
/* 116 */     IInventory baubles = BaublesApi.getBaubles(viewer);
/* 117 */     for (int a = 0; a < baubles.func_70302_i_(); a++) {
/* 118 */       if (baubles.func_70301_a(a).func_77973_b() instanceof IRevealer && 
/* 119 */         reveals(baubles.func_70301_a(a), viewer)) return true;
/*     */     
/*     */     } 
/*     */     
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */   
/* 127 */   private static boolean reveals(ItemStack stack, EntityPlayer player) { return ((IRevealer)stack.func_77973_b()).showNodes(stack, player); }
/*     */ 
/*     */ 
/*     */   
/* 131 */   public static Entity getPointedEntity(World world, Entity entity, double minrange, double range, float padding, boolean nonCollide) { return getPointedEntity(world, new RayTraceResult(entity, entity.func_174791_d().func_72441_c(0.0D, entity.func_70047_e(), 0.0D)), entity.func_70040_Z(), minrange, range, padding, nonCollide); }
/*     */ 
/*     */ 
/*     */   
/* 135 */   public static Entity getPointedEntity(World world, Entity entity, Vec3d lookVec, double minrange, double range, float padding) { return getPointedEntity(world, new RayTraceResult(entity, entity.func_174791_d().func_72441_c(0.0D, entity.func_70047_e(), 0.0D)), lookVec, minrange, range, padding, false); }
/*     */ 
/*     */ 
/*     */   
/* 139 */   public static Entity getPointedEntity(World world, RayTraceResult ray, Vec3d lookVec, double minrange, double range, float padding) { return getPointedEntity(world, ray, lookVec, minrange, range, padding, false); }
/*     */ 
/*     */   
/*     */   public static Entity getPointedEntity(World world, RayTraceResult ray, Vec3d lookVec, double minrange, double range, float padding, boolean nonCollide) {
/* 143 */     Entity pointedEntity = null;
/* 144 */     double d = range;
/* 145 */     Vec3d entityVec = new Vec3d(ray.field_72307_f.field_72450_a, ray.field_72307_f.field_72448_b, ray.field_72307_f.field_72449_c);
/* 146 */     Vec3d vec3d2 = entityVec.func_72441_c(lookVec.field_72450_a * d, lookVec.field_72448_b * d, lookVec.field_72449_c * d);
/*     */     
/* 148 */     float f1 = padding;
/* 149 */     AxisAlignedBB bb = (ray.field_72308_g != null) ? ray.field_72308_g.func_174813_aQ() : (new AxisAlignedBB(ray.field_72307_f.field_72450_a, ray.field_72307_f.field_72448_b, ray.field_72307_f.field_72449_c, ray.field_72307_f.field_72450_a, ray.field_72307_f.field_72448_b, ray.field_72307_f.field_72449_c)).func_186662_g(0.5D);
/* 150 */     List list = world.func_72839_b(ray.field_72308_g, bb
/* 151 */         .func_72321_a(lookVec.field_72450_a * d, lookVec.field_72448_b * d, lookVec.field_72449_c * d).func_72314_b(f1, f1, f1));
/* 152 */     double d2 = 0.0D;
/* 153 */     for (int i = 0; i < list.size(); i++) {
/* 154 */       Entity entity = (Entity)list.get(i);
/* 155 */       if (ray.field_72307_f.func_72438_d(entity.func_174791_d()) >= minrange)
/*     */       {
/* 157 */         if (entity.func_70067_L() || nonCollide) if (world.func_147447_a(ray.field_72307_f, new Vec3d(entity.field_70165_t, entity.field_70163_u + entity
/* 158 */                 .func_70047_e(), entity.field_70161_v), false, true, false) == null) {
/*     */ 
/*     */             
/* 161 */             float f2 = Math.max(0.8F, entity.func_70111_Y());
/* 162 */             AxisAlignedBB axisalignedbb = entity.func_174813_aQ().func_72314_b(f2, f2, f2);
/*     */             
/* 164 */             RayTraceResult RayTraceResult = axisalignedbb.func_72327_a(entityVec, vec3d2);
/* 165 */             if (axisalignedbb.func_72318_a(entityVec)) {
/* 166 */               if (0.0D < d2 || d2 == 0.0D) {
/* 167 */                 pointedEntity = entity;
/* 168 */                 d2 = 0.0D;
/*     */               }
/*     */             
/*     */             }
/* 172 */             else if (RayTraceResult != null) {
/*     */ 
/*     */               
/* 175 */               double d3 = entityVec.func_72438_d(RayTraceResult.field_72307_f);
/* 176 */               if (d3 < d2 || d2 == 0.0D)
/* 177 */               { pointedEntity = entity;
/* 178 */                 d2 = d3; } 
/*     */             } 
/*     */           }   } 
/* 181 */     }  return pointedEntity;
/*     */   }
/*     */   
/*     */   public static RayTraceResult getPointedEntityRay(World world, Entity ignoreEntity, Vec3d startVec, Vec3d lookVec, double minrange, double range, float padding, boolean nonCollide) {
/* 185 */     RayTraceResult pointedEntityRay = null;
/* 186 */     double d = range;
/* 187 */     Vec3d vec3d2 = startVec.func_72441_c(lookVec.field_72450_a * d, lookVec.field_72448_b * d, lookVec.field_72449_c * d);
/* 188 */     float f1 = padding;
/* 189 */     AxisAlignedBB bb = (ignoreEntity != null) ? ignoreEntity.func_174813_aQ() : (new AxisAlignedBB(startVec.field_72450_a, startVec.field_72448_b, startVec.field_72449_c, startVec.field_72450_a, startVec.field_72448_b, startVec.field_72449_c)).func_186662_g(0.5D);
/* 190 */     List list = world.func_72839_b(ignoreEntity, bb
/* 191 */         .func_72321_a(lookVec.field_72450_a * d, lookVec.field_72448_b * d, lookVec.field_72449_c * d).func_72314_b(f1, f1, f1));
/* 192 */     double d2 = 0.0D;
/* 193 */     for (int i = 0; i < list.size(); i++) {
/* 194 */       Entity entity = (Entity)list.get(i);
/* 195 */       if (startVec.func_72438_d(entity.func_174791_d()) >= minrange)
/*     */       {
/* 197 */         if (entity.func_70067_L() || nonCollide) if (world.func_147447_a(startVec, new Vec3d(entity.field_70165_t, entity.field_70163_u + entity
/* 198 */                 .func_70047_e(), entity.field_70161_v), false, true, false) == null) {
/*     */ 
/*     */             
/* 201 */             float f2 = Math.max(0.8F, entity.func_70111_Y());
/* 202 */             AxisAlignedBB axisalignedbb = entity.func_174813_aQ().func_72314_b(f2, f2, f2);
/* 203 */             RayTraceResult rayTraceResult = axisalignedbb.func_72327_a(startVec, vec3d2);
/* 204 */             if (axisalignedbb.func_72318_a(startVec)) {
/* 205 */               if (0.0D < d2 || d2 == 0.0D) {
/* 206 */                 pointedEntityRay = new RayTraceResult(entity, rayTraceResult.field_72307_f);
/* 207 */                 d2 = 0.0D;
/*     */               }
/*     */             
/*     */             }
/* 211 */             else if (rayTraceResult != null) {
/*     */ 
/*     */               
/* 214 */               double d3 = startVec.func_72438_d(rayTraceResult.field_72307_f);
/* 215 */               if (d3 < d2 || d2 == 0.0D)
/* 216 */               { pointedEntityRay = new RayTraceResult(entity, rayTraceResult.field_72307_f);
/* 217 */                 d2 = d3; } 
/*     */             } 
/*     */           }   } 
/* 220 */     }  return pointedEntityRay;
/*     */   }
/*     */   
/*     */   public static Entity getPointedEntity(World world, EntityLivingBase player, double range, Class<?> clazz) {
/* 224 */     Entity pointedEntity = null;
/* 225 */     double d = range;
/*     */     
/* 227 */     Vec3d vec3d = new Vec3d(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v);
/*     */     
/* 229 */     Vec3d vec3d1 = player.func_70040_Z();
/* 230 */     Vec3d vec3d2 = vec3d.func_72441_c(vec3d1.field_72450_a * d, vec3d1.field_72448_b * d, vec3d1.field_72449_c * d);
/*     */     
/* 232 */     float f1 = 1.1F;
/* 233 */     List list = world.func_72839_b(player, player
/* 234 */         .func_174813_aQ().func_72321_a(vec3d1.field_72450_a * d, vec3d1.field_72448_b * d, vec3d1.field_72449_c * d).func_72314_b(f1, f1, f1));
/* 235 */     double d2 = 0.0D;
/* 236 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 238 */       Entity entity = (Entity)list.get(i);
/* 239 */       if (entity.func_70067_L()) if (world
/* 240 */           .func_147447_a(new Vec3d(player.field_70165_t, player.field_70163_u + player
/* 241 */               .func_70047_e(), player.field_70161_v), new Vec3d(entity.field_70165_t, entity.field_70163_u + entity
/*     */ 
/*     */               
/* 244 */               .func_70047_e(), entity.field_70161_v), false, true, false) == null && 
/*     */           
/* 246 */           !clazz.isInstance(entity)) {
/*     */ 
/*     */           
/* 249 */           float f2 = Math.max(0.8F, entity.func_70111_Y());
/* 250 */           AxisAlignedBB axisalignedbb = entity.func_174813_aQ().func_72314_b(f2, f2, f2);
/*     */           
/* 252 */           RayTraceResult RayTraceResult = axisalignedbb.func_72327_a(vec3d, vec3d2);
/* 253 */           if (axisalignedbb.func_72318_a(vec3d)) {
/* 254 */             if (0.0D < d2 || d2 == 0.0D) {
/* 255 */               pointedEntity = entity;
/* 256 */               d2 = 0.0D;
/*     */             }
/*     */           
/*     */           }
/* 260 */           else if (RayTraceResult != null) {
/*     */ 
/*     */             
/* 263 */             double d3 = vec3d.func_72438_d(RayTraceResult.field_72307_f);
/* 264 */             if (d3 < d2 || d2 == 0.0D)
/* 265 */             { pointedEntity = entity;
/* 266 */               d2 = d3; } 
/*     */           } 
/*     */         }  
/* 269 */     }  return pointedEntity;
/*     */   }
/*     */   
/*     */   public static boolean canEntityBeSeen(Entity entity, TileEntity te) {
/* 273 */     return (te.func_145831_w().func_147447_a(new Vec3d(te
/* 274 */           .func_174877_v().func_177958_n() + 0.5D, te.func_174877_v().func_177956_o() + 1.25D, te.func_174877_v().func_177952_p() + 0.5D), new Vec3d(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v), false, true, false) == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 280 */   public static boolean canEntityBeSeen(Entity lookingEntity, double x, double y, double z) { return (lookingEntity.field_70170_p.func_147447_a(new Vec3d(x, y, z), new Vec3d(lookingEntity.field_70165_t, lookingEntity.field_70163_u, lookingEntity.field_70161_v), false, true, false) == null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 287 */   public static boolean canEntityBeSeen(Entity lookingEntity, Entity targetEntity) { return (lookingEntity.field_70170_p.func_147447_a(new Vec3d(lookingEntity.field_70165_t, lookingEntity.field_70163_u + (lookingEntity.field_70131_O / 2.0F), lookingEntity.field_70161_v), new Vec3d(targetEntity.field_70165_t, targetEntity.field_70163_u + (targetEntity.field_70131_O / 2.0F), targetEntity.field_70161_v), false, true, false) == null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 342 */   public static void resetFloatCounter(EntityPlayerMP player) { player.field_71135_a.field_147365_f = 0; }
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T extends Entity> List<T> getEntitiesInRange(World world, BlockPos pos, Entity entity, Class<? extends T> classEntity, double range) {
/* 347 */     return getEntitiesInRange(world, pos
/* 348 */         .func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, entity, classEntity, range);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Entity> List<T> getEntitiesInRange(World world, double x, double y, double z, Entity entity, Class<? extends T> classEntity, double range) {
/* 353 */     ArrayList<T> out = new ArrayList<T>();
/* 354 */     List list = world.func_72872_a(classEntity, (new AxisAlignedBB(x, y, z, x, y, z))
/* 355 */         .func_72314_b(range, range, range));
/* 356 */     if (list.size() > 0) {
/* 357 */       for (Object e : list) {
/* 358 */         Entity ent = (Entity)e;
/* 359 */         if (entity != null && entity.func_145782_y() == ent.func_145782_y()) {
/*     */           continue;
/*     */         }
/* 362 */         out.add(ent);
/*     */       } 
/*     */     }
/*     */     
/* 366 */     return out;
/*     */   }
/*     */   
/*     */   public static <T extends Entity> List<T> getEntitiesInRangeSorted(World world, Entity entity, Class<? extends T> classEntity, double range) {
/* 370 */     List<T> list = getEntitiesInRange(world, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity, classEntity, range);
/* 371 */     return (ArrayList)list.stream().sorted(new EntityDistComparator(entity)).collect(Collectors.toList());
/*     */   }
/*     */ 
/*     */   
/*     */   public static class EntityDistComparator
/*     */     extends Object
/*     */     implements Comparator<Entity>
/*     */   {
/*     */     private Entity source;
/*     */     
/* 381 */     public EntityDistComparator(Entity source) { this.source = source; }
/*     */ 
/*     */ 
/*     */     
/*     */     public int compare(Entity a, Entity b) {
/* 386 */       if (a.equals(b))
/*     */       {
/* 388 */         return 0;
/*     */       }
/* 390 */       double da = this.source.func_174791_d().func_72436_e(a.func_174791_d());
/* 391 */       double db = this.source.func_174791_d().func_72436_e(b.func_174791_d());
/* 392 */       return (da < db) ? -1 : ((da > db) ? 1 : 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isVisibleTo(float fov, Entity ent, Entity ent2, float range) {
/* 398 */     double[] x = { ent2.field_70165_t, (ent2.func_174813_aQ()).field_72338_b + (ent2.field_70131_O / 2.0F), ent2.field_70161_v };
/* 399 */     double[] t = { ent.field_70165_t, (ent.func_174813_aQ()).field_72338_b + ent.func_70047_e(), ent.field_70161_v };
/* 400 */     Vec3d q = ent.func_70040_Z();
/* 401 */     q = new Vec3d(q.field_72450_a * range, q.field_72448_b * range, q.field_72449_c * range);
/* 402 */     Vec3d l = q.func_72441_c(ent.field_70165_t, (ent.func_174813_aQ()).field_72338_b + ent.func_70047_e(), ent.field_70161_v);
/* 403 */     double[] b = { l.field_72450_a, l.field_72448_b, l.field_72449_c };
/* 404 */     return Utils.isLyingInCone(x, t, b, fov);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isVisibleTo(float fov, Entity ent, double xx, double yy, double zz, float range) {
/* 410 */     double[] x = { xx, yy, zz };
/* 411 */     double[] t = { ent.field_70165_t, (ent.func_174813_aQ()).field_72338_b + ent.func_70047_e(), ent.field_70161_v };
/* 412 */     Vec3d q = ent.func_70040_Z();
/* 413 */     q = new Vec3d(q.field_72450_a * range, q.field_72448_b * range, q.field_72449_c * range);
/* 414 */     Vec3d l = q.func_72441_c(ent.field_70165_t, (ent.func_174813_aQ()).field_72338_b + ent.func_70047_e(), ent.field_70161_v);
/* 415 */     double[] b = { l.field_72450_a, l.field_72448_b, l.field_72449_c };
/* 416 */     return Utils.isLyingInCone(x, t, b, fov);
/*     */   }
/*     */ 
/*     */   
/*     */   public static EntityItem entityDropSpecialItem(Entity entity, ItemStack stack, float dropheight) {
/* 421 */     if (stack.func_190916_E() != 0 && stack.func_77973_b() != null) {
/*     */       
/* 423 */       EntitySpecialItem entityitem = new EntitySpecialItem(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u + dropheight, entity.field_70161_v, stack);
/*     */       
/* 425 */       entityitem.func_174869_p();
/* 426 */       entityitem.field_70181_x = 0.10000000149011612D;
/* 427 */       entityitem.field_70159_w = 0.0D;
/* 428 */       entityitem.field_70179_y = 0.0D;
/* 429 */       if (entity.captureDrops) {
/*     */         
/* 431 */         entity.capturedDrops.add(entityitem);
/*     */       }
/*     */       else {
/*     */         
/* 435 */         entity.field_70170_p.func_72838_d(entityitem);
/*     */       } 
/* 437 */       return entityitem;
/*     */     } 
/*     */ 
/*     */     
/* 441 */     return null;
/*     */   }
/*     */   
/*     */   public static void makeChampion(EntityMob entity, boolean persist) {
/*     */     int bh;
/*     */     IAttributeInstance mai, sai;
/*     */     
/* 448 */     try { if (entity.func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD).func_111126_e() > -2.0D)
/* 449 */         return;  } catch (Exception e)
/*     */     { return; }
/*     */ 
/*     */     
/* 453 */     int type = entity.field_70170_p.field_73012_v.nextInt(ChampionModifier.mods.length);
/*     */     
/* 455 */     if (entity instanceof net.minecraft.entity.monster.EntityCreeper) {
/* 456 */       type = 0;
/*     */     }
/*     */     
/* 459 */     IAttributeInstance modai = entity.func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD);
/* 460 */     modai.func_111124_b((ChampionModifier.mods[type]).attributeMod);
/* 461 */     modai.func_111121_a((ChampionModifier.mods[type]).attributeMod);
/*     */     
/* 463 */     if (!(entity instanceof EntityThaumcraftBoss)) {
/* 464 */       IAttributeInstance iattributeinstance = entity.func_110148_a(SharedMonsterAttributes.field_111267_a);
/* 465 */       iattributeinstance.func_111124_b(CHAMPION_HEALTH);
/* 466 */       iattributeinstance.func_111121_a(CHAMPION_HEALTH);
/* 467 */       IAttributeInstance iattributeinstance2 = entity.func_110148_a(SharedMonsterAttributes.field_111264_e);
/* 468 */       iattributeinstance2.func_111124_b(CHAMPION_DAMAGE);
/* 469 */       iattributeinstance2.func_111121_a(CHAMPION_DAMAGE);
/* 470 */       entity.func_70691_i(25.0F);
/* 471 */       entity.func_96094_a(ChampionModifier.mods[type].getModNameLocalized() + " " + entity.func_70005_c_());
/*     */     } else {
/* 473 */       ((EntityThaumcraftBoss)entity).generateName();
/*     */     } 
/*     */     
/* 476 */     if (persist) entity.func_110163_bv();
/*     */     
/* 478 */     switch (type) {
/*     */       case 0:
/* 480 */         sai = entity.func_110148_a(SharedMonsterAttributes.field_111263_d);
/* 481 */         sai.func_111124_b(BOLDBUFF);
/* 482 */         sai.func_111121_a(BOLDBUFF);
/*     */         break;
/*     */       case 3:
/* 485 */         mai = entity.func_110148_a(SharedMonsterAttributes.field_111264_e);
/* 486 */         mai.func_111124_b(MIGHTYBUFF);
/* 487 */         mai.func_111121_a(MIGHTYBUFF);
/*     */         break;
/*     */       case 5:
/* 490 */         bh = (int)entity.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() / 2;
/* 491 */         entity.func_110149_m(entity.func_110139_bj() + bh);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void makeTainted(EntityLivingBase target) {
/*     */     try {
/* 499 */       if (target.func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD) != null && target
/* 500 */         .func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD).func_111126_e() > -1.0D) {
/*     */         return;
/*     */       }
/* 503 */     } catch (Exception e) {
/* 504 */       e.printStackTrace();
/*     */       return;
/*     */     } 
/* 507 */     int type = 13;
/*     */     
/* 509 */     IAttributeInstance modai = target.func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD);
/* 510 */     if (modai == null)
/* 511 */       return;  if (target.func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD).func_111126_e() == -1.0D) {
/* 512 */       modai.func_111121_a(ChampionModifier.ATTRIBUTE_MINUS_ONE);
/*     */     }
/* 514 */     modai.func_111124_b((ChampionModifier.mods[type]).attributeMod);
/* 515 */     modai.func_111121_a((ChampionModifier.mods[type]).attributeMod);
/* 516 */     if (!(target instanceof EntityThaumcraftBoss)) {
/* 517 */       IAttributeInstance iattributeinstance = target.func_110148_a(SharedMonsterAttributes.field_111267_a);
/* 518 */       iattributeinstance.func_111124_b(HPBUFF[5]);
/* 519 */       iattributeinstance.func_111121_a(HPBUFF[5]);
/* 520 */       IAttributeInstance iattributeinstance2 = target.func_110148_a(SharedMonsterAttributes.field_111264_e);
/* 521 */       if (iattributeinstance2 == null) {
/* 522 */         target.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
/* 523 */         target.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(Math.max(2.0F, (target.field_70131_O + target.field_70130_N) * 2.0F));
/*     */       } else {
/* 525 */         iattributeinstance2.func_111124_b(DMGBUFF[0]);
/* 526 */         iattributeinstance2.func_111121_a(DMGBUFF[0]);
/*     */       } 
/* 528 */       target.func_70691_i(25.0F);
/*     */     } else {
/* 530 */       ((EntityThaumcraftBoss)target).generateName();
/*     */     } 
/*     */   }
/*     */   
/* 534 */   public static final AttributeModifier CHAMPION_HEALTH = new AttributeModifier(UUID.fromString("a62bef38-48cc-42a6-ac5e-ef913841c4fd"), "Champion health buff", 100.0D, 0);
/* 535 */   public static final AttributeModifier CHAMPION_DAMAGE = new AttributeModifier(UUID.fromString("a340d2db-d881-4c25-ac62-f0ad14cd63b0"), "Champion damage buff", 2.0D, 2);
/* 536 */   public static final AttributeModifier BOLDBUFF = new AttributeModifier(UUID.fromString("4b1edd33-caa9-47ae-a702-d86c05701037"), "Bold speed boost", 0.3D, 1);
/* 537 */   public static final AttributeModifier MIGHTYBUFF = new AttributeModifier(UUID.fromString("7163897f-07f5-49b3-9ce4-b74beb83d2d3"), "Mighty damage boost", 2.0D, 2);
/* 538 */   public static final AttributeModifier[] HPBUFF = { new AttributeModifier(
/* 539 */         UUID.fromString("54d621c1-dd4d-4b43-8bd2-5531c8875797"), "HEALTH BUFF 1", 50.0D, 0), new AttributeModifier(
/* 540 */         UUID.fromString("f51257dc-b7fa-4f7a-92d7-75d68e8592c4"), "HEALTH BUFF 2", 50.0D, 0), new AttributeModifier(
/* 541 */         UUID.fromString("3d6b2e42-4141-4364-b76d-0e8664bbd0bb"), "HEALTH BUFF 3", 50.0D, 0), new AttributeModifier(
/* 542 */         UUID.fromString("02c97a08-801c-4131-afa2-1427a6151934"), "HEALTH BUFF 4", 50.0D, 0), new AttributeModifier(
/* 543 */         UUID.fromString("0f354f6a-33c5-40be-93be-81b1338567f1"), "HEALTH BUFF 5", 50.0D, 0), new AttributeModifier(
/* 544 */         UUID.fromString("0f354f6a-33c5-40be-93be-81b1338567f1"), "HEALTH BUFF 6", 25.0D, 0) };
/* 545 */   public static final AttributeModifier[] DMGBUFF = { new AttributeModifier(
/* 546 */         UUID.fromString("534f8c57-929a-48cf-bbd6-0fd851030748"), "DAMAGE BUFF 1", 0.5D, 0), new AttributeModifier(
/* 547 */         UUID.fromString("d317a76e-0e7c-4c61-acfd-9fa286053b32"), "DAMAGE BUFF 2", 0.5D, 0), new AttributeModifier(
/* 548 */         UUID.fromString("ff462d63-26a2-4363-830e-143ed97e2a4f"), "DAMAGE BUFF 3", 0.5D, 0), new AttributeModifier(
/* 549 */         UUID.fromString("cf1eb39e-0c67-495f-887c-0d3080828d2f"), "DAMAGE BUFF 4", 0.5D, 0), new AttributeModifier(
/* 550 */         UUID.fromString("3cfab9da-2701-43d8-ac07-885f16fa4117"), "DAMAGE BUFF 5", 0.5D, 0) };
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\li\\utils\EntityUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */