/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.WorldClient;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraftforge.fml.client.FMLClientHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ 
/*    */ public class PacketFXSlash extends Object implements IMessage, IMessageHandler<PacketFXSlash, IMessage> {
/*    */   private int source;
/*    */   private int target;
/*    */   
/*    */   public PacketFXSlash() {}
/*    */   
/*    */   public PacketFXSlash(int source, int target) {
/* 22 */     this.source = source;
/* 23 */     this.target = target;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 28 */     buffer.writeInt(this.source);
/* 29 */     buffer.writeInt(this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 34 */     this.source = buffer.readInt();
/* 35 */     this.target = buffer.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(PacketFXSlash message, MessageContext ctx) {
/* 41 */     Minecraft mc = FMLClientHandler.instance().getClient();
/* 42 */     WorldClient world = mc.field_71441_e;
/*    */     
/* 44 */     Entity var2 = getEntityByID(message.source, mc, world);
/* 45 */     Entity var3 = getEntityByID(message.target, mc, world);
/* 46 */     if (var2 != null && var3 != null) {
/* 47 */       FXDispatcher.INSTANCE.drawSlash(var2.field_70165_t, 
/* 48 */           (var2.func_174813_aQ()).field_72338_b + (var2.field_70131_O / 2.0F), var2.field_70161_v, var3.field_70165_t, 
/* 49 */           (var3.func_174813_aQ()).field_72338_b + (var3.field_70131_O / 2.0F), var3.field_70161_v, 8);
/*    */     }
/*    */     
/* 52 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/* 58 */   private Entity getEntityByID(int par1, Minecraft mc, WorldClient world) { return (par1 == mc.field_71439_g.func_145782_y()) ? mc.field_71439_g : world.func_73045_a(par1); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\fx\PacketFXSlash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */