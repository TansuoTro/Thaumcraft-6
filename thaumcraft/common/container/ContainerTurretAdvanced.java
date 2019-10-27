/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.common.container.slot.SlotTurretBasic;
/*     */ import thaumcraft.common.entities.construct.EntityTurretCrossbowAdvanced;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerTurretAdvanced
/*     */   extends Container
/*     */ {
/*     */   private EntityTurretCrossbowAdvanced turret;
/*     */   private EntityPlayer player;
/*     */   private final World theWorld;
/*     */   
/*     */   public ContainerTurretAdvanced(InventoryPlayer par1InventoryPlayer, World par3World, EntityTurretCrossbowAdvanced ent) {
/*  25 */     this.turret = ent;
/*  26 */     this.theWorld = par3World;
/*  27 */     this.player = par1InventoryPlayer.field_70458_d;
/*     */     
/*  29 */     func_75146_a(new SlotTurretBasic(this.turret, 0, 42, 29));
/*     */     
/*     */     int i;
/*  32 */     for (i = 0; i < 3; i++) {
/*     */       
/*  34 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  36 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     } 
/*     */     
/*  40 */     for (i = 0; i < 9; i++)
/*     */     {
/*  42 */       func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75140_a(EntityPlayer par1EntityPlayer, int par2) {
/*  48 */     if (par2 == 1) {
/*  49 */       this.turret.setTargetAnimal(!this.turret.getTargetAnimal());
/*  50 */       return true;
/*     */     } 
/*  52 */     if (par2 == 2) {
/*  53 */       this.turret.setTargetMob(!this.turret.getTargetMob());
/*  54 */       return true;
/*     */     } 
/*  56 */     if (par2 == 3) {
/*  57 */       this.turret.setTargetPlayer(!this.turret.getTargetPlayer());
/*  58 */       return true;
/*     */     } 
/*  60 */     if (par2 == 4) {
/*  61 */       this.turret.setTargetFriendly(!this.turret.getTargetFriendly());
/*  62 */       return true;
/*     */     } 
/*  64 */     return super.func_75140_a(par1EntityPlayer, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_75137_b(int par1, int par2) {}
/*     */ 
/*     */ 
/*     */   
/*  74 */   public boolean func_75145_c(EntityPlayer par1EntityPlayer) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot) {
/*  82 */     ItemStack stack = ItemStack.field_190927_a;
/*  83 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*     */ 
/*     */     
/*  86 */     if (slotObject != null && slotObject.func_75216_d()) {
/*  87 */       ItemStack stackInSlot = slotObject.func_75211_c();
/*  88 */       stack = stackInSlot.func_77946_l();
/*     */       
/*  90 */       if (slot == 0) {
/*  91 */         if (!func_75135_a(stackInSlot, 1, this.field_75151_b.size(), true)) {
/*  92 */           return ItemStack.field_190927_a;
/*     */         }
/*     */       }
/*  95 */       else if (!func_75135_a(stackInSlot, 0, 1, false)) {
/*  96 */         return ItemStack.field_190927_a;
/*     */       } 
/*     */       
/*  99 */       if (stackInSlot.func_190916_E() == 0) {
/* 100 */         slotObject.func_75215_d(ItemStack.field_190927_a);
/*     */       } else {
/* 102 */         slotObject.func_75218_e();
/*     */       } 
/*     */     } 
/*     */     
/* 106 */     return stack;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\ContainerTurretAdvanced.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */