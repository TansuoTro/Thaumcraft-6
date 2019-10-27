/*    */ package thaumcraft.api.damagesource;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.EntityDamageSource;
/*    */ 
/*    */ public class DamageSourceThaumcraft
/*    */   extends DamageSource
/*    */ {
/* 10 */   public static DamageSource taint = (new DamageSourceThaumcraft("taint")).func_76348_h().func_82726_p();
/* 11 */   public static DamageSource tentacle = new DamageSourceThaumcraft("tentacle");
/* 12 */   public static DamageSource swarm = new DamageSourceThaumcraft("swarm");
/* 13 */   public static DamageSource dissolve = (new DamageSourceThaumcraft("dissolve")).func_76348_h(); private boolean isUnblockable; private boolean isDamageAllowedInCreativeMode; private float hungerDamage; private boolean fireDamage;
/*    */   
/*    */   protected DamageSourceThaumcraft(String par1Str) {
/* 16 */     super(par1Str);
/*    */ 
/*    */ 
/*    */     
/* 20 */     this.isUnblockable = false;
/* 21 */     this.isDamageAllowedInCreativeMode = false;
/* 22 */     this.hungerDamage = 0.3F;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 34 */     this.magicDamage = false;
/* 35 */     this.explosion = false;
/*    */   }
/*    */   private boolean projectile; private boolean difficultyScaled; private boolean magicDamage; private boolean explosion;
/*    */   
/* 39 */   public static DamageSource causeSwarmDamage(EntityLivingBase par0EntityLiving) { return new EntityDamageSource("swarm", par0EntityLiving); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public static DamageSource causeTentacleDamage(EntityLivingBase par0EntityLiving) { return new EntityDamageSource("tentacle", par0EntityLiving); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\damagesource\DamageSourceThaumcraft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */