/*     */ package thaumcraft.proxies;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelSlime;
/*     */ import net.minecraft.client.renderer.entity.RenderEntityItem;
/*     */ import net.minecraft.client.renderer.entity.RenderSnowball;
/*     */ import net.minecraftforge.fml.client.registry.RenderingRegistry;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.renderers.entity.RenderFallingTaint;
/*     */ import thaumcraft.client.renderers.entity.RenderFluxRift;
/*     */ import thaumcraft.client.renderers.entity.RenderSpecialItem;
/*     */ import thaumcraft.client.renderers.entity.construct.RenderArcaneBore;
/*     */ import thaumcraft.client.renderers.entity.construct.RenderCultistPortalGreater;
/*     */ import thaumcraft.client.renderers.entity.construct.RenderCultistPortalLesser;
/*     */ import thaumcraft.client.renderers.entity.construct.RenderTurretCrossbow;
/*     */ import thaumcraft.client.renderers.entity.construct.RenderTurretCrossbowAdvanced;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderBrainyZombie;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderCultist;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderCultistLeader;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderEldritchCrab;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderEldritchGolem;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderEldritchGuardian;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderFireBat;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderInhabitedZombie;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderMindSpider;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderPech;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderSpellBat;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderTaintCrawler;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderTaintSeed;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderTaintSwarm;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderTaintacle;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderThaumicSlime;
/*     */ import thaumcraft.client.renderers.entity.mob.RenderWisp;
/*     */ import thaumcraft.client.renderers.entity.projectile.RenderDart;
/*     */ import thaumcraft.client.renderers.entity.projectile.RenderEldritchOrb;
/*     */ import thaumcraft.client.renderers.entity.projectile.RenderElectricOrb;
/*     */ import thaumcraft.client.renderers.entity.projectile.RenderFocusCloud;
/*     */ import thaumcraft.client.renderers.entity.projectile.RenderFocusMine;
/*     */ import thaumcraft.client.renderers.entity.projectile.RenderGrapple;
/*     */ import thaumcraft.client.renderers.entity.projectile.RenderNoProjectile;
/*     */ import thaumcraft.client.renderers.models.entity.ModelEldritchGolem;
/*     */ import thaumcraft.client.renderers.models.entity.ModelEldritchGuardian;
/*     */ import thaumcraft.client.renderers.models.entity.ModelPech;
/*     */ import thaumcraft.client.renderers.models.entity.ModelTaintSeed;
/*     */ import thaumcraft.common.golems.client.RenderThaumcraftGolem;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProxyEntities
/*     */ {
/*     */   public void setupEntityRenderers() {
/*  93 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.EntitySpecialItem.class, new RenderSpecialItem(Minecraft.func_71410_x().func_175598_ae(), Minecraft.func_71410_x().func_175599_af()));
/*  94 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.EntityFollowingItem.class, new RenderEntityItem(Minecraft.func_71410_x().func_175598_ae(), Minecraft.func_71410_x().func_175599_af()));
/*     */     
/*  96 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.EntityFallingTaint.class, new RenderFallingTaint(Minecraft.func_71410_x().func_175598_ae()));
/*  97 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.EntityFluxRift.class, new RenderFluxRift(Minecraft.func_71410_x().func_175598_ae()));
/*     */ 
/*     */     
/* 100 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.EntityBrainyZombie.class, new RenderBrainyZombie(Minecraft.func_71410_x().func_175598_ae()));
/* 101 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.EntityInhabitedZombie.class, new RenderInhabitedZombie(Minecraft.func_71410_x().func_175598_ae()));
/* 102 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.EntityGiantBrainyZombie.class, new RenderBrainyZombie(Minecraft.func_71410_x().func_175598_ae()));
/* 103 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.EntityFireBat.class, new RenderFireBat(Minecraft.func_71410_x().func_175598_ae()));
/* 104 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.EntitySpellBat.class, new RenderSpellBat(Minecraft.func_71410_x().func_175598_ae()));
/* 105 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.EntityPech.class, new RenderPech(Minecraft.func_71410_x().func_175598_ae(), new ModelPech(), 0.25F));
/* 106 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.EntityWisp.class, new RenderWisp(Minecraft.func_71410_x().func_175598_ae()));
/* 107 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.EntityThaumicSlime.class, new RenderThaumicSlime(Minecraft.func_71410_x().func_175598_ae(), new ModelSlime(16), 0.25F));
/*     */     
/* 109 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.tainted.EntityTaintCrawler.class, new RenderTaintCrawler(Minecraft.func_71410_x().func_175598_ae()));
/* 110 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.tainted.EntityTaintSeed.class, new RenderTaintSeed(Minecraft.func_71410_x().func_175598_ae()));
/* 111 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.tainted.EntityTaintSeedPrime.class, new RenderTaintSeed(Minecraft.func_71410_x().func_175598_ae(), new ModelTaintSeed(), 0.6F));
/* 112 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.tainted.EntityTaintacle.class, new RenderTaintacle(Minecraft.func_71410_x().func_175598_ae(), 0.6F, 10));
/* 113 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.tainted.EntityTaintacleSmall.class, new RenderTaintacle(Minecraft.func_71410_x().func_175598_ae(), 0.2F, 6));
/* 114 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.tainted.EntityTaintSwarm.class, new RenderTaintSwarm(Minecraft.func_71410_x().func_175598_ae()));
/*     */     
/* 116 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.cult.EntityCultistKnight.class, new RenderCultist(Minecraft.func_71410_x().func_175598_ae()));
/* 117 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.boss.EntityCultistLeader.class, new RenderCultistLeader(Minecraft.func_71410_x().func_175598_ae()));
/* 118 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.cult.EntityCultistCleric.class, new RenderCultist(Minecraft.func_71410_x().func_175598_ae()));
/*     */     
/* 120 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.EntityEldritchGuardian.class, new RenderEldritchGuardian(Minecraft.func_71410_x().func_175598_ae(), new ModelEldritchGuardian(), 0.5F));
/* 121 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.EntityMindSpider.class, new RenderMindSpider(Minecraft.func_71410_x().func_175598_ae()));
/* 122 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.EntityEldritchCrab.class, new RenderEldritchCrab(Minecraft.func_71410_x().func_175598_ae()));
/*     */ 
/*     */     
/* 125 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.boss.EntityTaintacleGiant.class, new RenderTaintacle(Minecraft.func_71410_x().func_175598_ae(), 1.0F, 14));
/* 126 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.boss.EntityCultistPortalGreater.class, new RenderCultistPortalGreater(Minecraft.func_71410_x().func_175598_ae()));
/* 127 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser.class, new RenderCultistPortalLesser(Minecraft.func_71410_x().func_175598_ae()));
/* 128 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.boss.EntityEldritchWarden.class, new RenderEldritchGuardian(Minecraft.func_71410_x().func_175598_ae(), new ModelEldritchGuardian(), 0.6F));
/* 129 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.monster.boss.EntityEldritchGolem.class, new RenderEldritchGolem(Minecraft.func_71410_x().func_175598_ae(), new ModelEldritchGolem(), 0.5F));
/*     */ 
/*     */ 
/*     */     
/* 133 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.projectile.EntityFocusProjectile.class, new RenderNoProjectile(Minecraft.func_71410_x().func_175598_ae()));
/* 134 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.projectile.EntityFocusCloud.class, new RenderFocusCloud(Minecraft.func_71410_x().func_175598_ae()));
/* 135 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.projectile.EntityFocusMine.class, new RenderFocusMine(Minecraft.func_71410_x().func_175598_ae()));
/* 136 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.projectile.EntityAlumentum.class, new RenderNoProjectile(Minecraft.func_71410_x().func_175598_ae()));
/* 137 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.projectile.EntityGolemOrb.class, new RenderElectricOrb(Minecraft.func_71410_x().func_175598_ae()));
/* 138 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.projectile.EntityGolemDart.class, new RenderDart(Minecraft.func_71410_x().func_175598_ae()));
/* 139 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.projectile.EntityBottleTaint.class, new RenderSnowball(Minecraft.func_71410_x().func_175598_ae(), ItemsTC.bottleTaint, Minecraft.func_71410_x().func_175599_af()));
/* 140 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.projectile.EntityEldritchOrb.class, new RenderEldritchOrb(Minecraft.func_71410_x().func_175598_ae()));
/* 141 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.projectile.EntityGrapple.class, new RenderGrapple(Minecraft.func_71410_x().func_175598_ae()));
/* 142 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.projectile.EntityCausalityCollapser.class, new RenderNoProjectile(Minecraft.func_71410_x().func_175598_ae()));
/*     */ 
/*     */ 
/*     */     
/* 146 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.construct.EntityTurretCrossbowAdvanced.class, new RenderTurretCrossbowAdvanced(Minecraft.func_71410_x().func_175598_ae()));
/* 147 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.construct.EntityTurretCrossbow.class, new RenderTurretCrossbow(Minecraft.func_71410_x().func_175598_ae()));
/* 148 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.entities.construct.EntityArcaneBore.class, new RenderArcaneBore(Minecraft.func_71410_x().func_175598_ae()));
/* 149 */     RenderingRegistry.registerEntityRenderingHandler(thaumcraft.common.golems.EntityThaumcraftGolem.class, new RenderThaumcraftGolem(Minecraft.func_71410_x().func_175598_ae()));
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\proxies\ProxyEntities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */