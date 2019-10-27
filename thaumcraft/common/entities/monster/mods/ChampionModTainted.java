/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.client.renderer.entity.RenderLivingBase;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*    */ import net.minecraft.entity.ai.EntityAILookIdle;
/*    */ import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
/*    */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*    */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*    */ import net.minecraft.entity.ai.EntityAISwimming;
/*    */ import net.minecraft.entity.ai.EntityAIWander;
/*    */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.IAttribute;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.entity.ai.attributes.RangedAttribute;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ import thaumcraft.client.renderers.entity.mob.LayerTainted;
/*    */ import thaumcraft.common.entities.ai.combat.EntityCritterAIAttackMelee;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChampionModTainted
/*    */   implements IChampionModifierEffect
/*    */ {
/* 38 */   public static final IAttribute TAINTED_MOD = (new RangedAttribute((IAttribute)null, "tc.mobmodtaint", 0.0D, 0.0D, 1.0D)).func_111117_a("Tainted modifier");
/*    */ 
/*    */   
/*    */   public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float amount) {
/* 42 */     resetAI((EntityCreature)boss);
/* 43 */     return amount;
/*    */   }
/*    */   
/*    */   public static void resetAI(EntityCreature critter) {
/* 47 */     IAttributeInstance modai = critter.func_110148_a(TAINTED_MOD);
/* 48 */     if (!(critter instanceof net.minecraft.entity.monster.EntityMob) && modai.func_111126_e() == 0.0D) {
/*    */       try {
/* 50 */         critter.field_70714_bg.field_75782_a.clear();
/* 51 */         critter.field_70715_bh.field_75782_a.clear();
/* 52 */         critter.field_70714_bg.func_75776_a(0, new EntityAISwimming(critter));
/* 53 */         critter.field_70714_bg.func_75776_a(2, new EntityCritterAIAttackMelee(critter, 1.2D, false));
/* 54 */         critter.field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsRestriction(critter, 1.0D));
/* 55 */         critter.field_70714_bg.func_75776_a(7, new EntityAIWander(critter, 1.0D));
/* 56 */         critter.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(critter, net.minecraft.entity.player.EntityPlayer.class, 8.0F));
/* 57 */         critter.field_70714_bg.func_75776_a(8, new EntityAILookIdle(critter));
/* 58 */         critter.field_70714_bg.func_75776_a(6, new EntityAIMoveThroughVillage(critter, 1.0D, false));
/* 59 */         critter.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(critter, true, new Class[] { net.minecraft.entity.monster.EntityPigZombie.class }));
/* 60 */         critter.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(critter, net.minecraft.entity.player.EntityPlayer.class, true));
/* 61 */         modai.func_111124_b(new AttributeModifier(UUID.fromString("2cb22137-a9d8-4417-ae06-de0e70f11b4c"), "istainted", 0.0D, 0));
/* 62 */         modai.func_111121_a(new AttributeModifier(UUID.fromString("2cb22137-a9d8-4417-ae06-de0e70f11b4c"), "istainted", 1.0D, 0));
/* 63 */       } catch (Exception exception) {}
/*    */     }
/*    */     
/* 66 */     IAttributeInstance iattributeinstance2 = critter.func_110148_a(SharedMonsterAttributes.field_111264_e);
/* 67 */     if (iattributeinstance2 == null) {
/* 68 */       critter.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
/* 69 */       critter.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(Math.max(2.0F, (critter.field_70131_O + critter.field_70130_N) * 2.0F));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void preRender(EntityLivingBase boss, RenderLivingBase renderLivingBase) {
/* 78 */     if (!LayerTainted.taintLayers.contains(Integer.valueOf(boss.func_145782_y()))) {
/* 79 */       renderLivingBase.func_177094_a(new LayerTainted(boss.func_145782_y(), renderLivingBase, renderLivingBase.func_177087_b()));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void showFX(EntityLivingBase boss) {
/* 86 */     float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 87 */     float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
/* 88 */     float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
/* 89 */     FXDispatcher.INSTANCE.drawGenericParticles(
/* 90 */         (boss.func_174813_aQ()).field_72340_a + w, (boss.func_174813_aQ()).field_72338_b + h, (boss.func_174813_aQ()).field_72339_c + d, 0.0D, -0.01D, 0.0D, 0.1F + boss.field_70170_p.field_73012_v
/*    */         
/* 92 */         .nextFloat() * 0.2F, 0.0F, 0.1F + boss.field_70170_p.field_73012_v.nextFloat() * 0.1F, 0.25F, false, 1, 5, 1, 6 + boss.field_70170_p.field_73012_v
/* 93 */         .nextInt(6), 0, 2.0F + boss.field_70170_p.field_73012_v
/* 94 */         .nextFloat(), 0.5F, 1);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\mods\ChampionModTainted.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */