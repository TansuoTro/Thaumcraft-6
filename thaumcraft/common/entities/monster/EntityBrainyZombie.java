/*    */ package thaumcraft.common.entities.monster;
/*    */ 
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*    */ import net.minecraft.entity.monster.EntityZombie;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityBrainyZombie
/*    */   extends EntityZombie
/*    */ {
/*    */   protected void func_110147_ax() {
/* 17 */     super.func_110147_ax();
/* 18 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(25.0D);
/* 19 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
/* 20 */     func_110148_a(field_110186_bp).func_111128_a(0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityBrainyZombie(World world) {
/* 25 */     super(world);
/* 26 */     this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false, new Class[0]));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   public int func_70658_aO() { return super.func_70658_aO() + 1; }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_184610_a(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
/* 37 */     if (this.field_70170_p.field_73012_v.nextInt(10) - lootingModifier <= 4) func_70099_a(new ItemStack(ItemsTC.brain), 1.5F); 
/* 38 */     super.func_184610_a(wasRecentlyHit, lootingModifier, source);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\EntityBrainyZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */