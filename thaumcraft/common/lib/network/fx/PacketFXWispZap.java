/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.WorldClient;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraftforge.fml.client.FMLClientHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ import thaumcraft.common.entities.monster.EntityWisp;
/*    */ 
/*    */ public class PacketFXWispZap
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketFXWispZap, IMessage> {
/*    */   private int source;
/*    */   private int target;
/*    */   
/*    */   public PacketFXWispZap() {}
/*    */   
/*    */   public PacketFXWispZap(int source, int target) {
/* 27 */     this.source = source;
/* 28 */     this.target = target;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 33 */     buffer.writeInt(this.source);
/* 34 */     buffer.writeInt(this.target);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 39 */     this.source = buffer.readInt();
/* 40 */     this.target = buffer.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(PacketFXWispZap message, MessageContext ctx) {
/* 45 */     Minecraft mc = FMLClientHandler.instance().getClient();
/* 46 */     WorldClient world = mc.field_71441_e;
/*    */     
/* 48 */     Entity var2 = getEntityByID(message.source, mc, world);
/* 49 */     Entity var3 = getEntityByID(message.target, mc, world);
/* 50 */     if (var2 != null && var3 != null) {
/* 51 */       float r = 1.0F;
/* 52 */       float g = 1.0F;
/* 53 */       float b = 1.0F;
/* 54 */       if (var2 instanceof EntityWisp) {
/* 55 */         Color c = new Color(Aspect.getAspect(((EntityWisp)var2).getType()).getColor());
/* 56 */         r = c.getRed() / 255.0F;
/* 57 */         g = c.getGreen() / 255.0F;
/* 58 */         b = c.getBlue() / 255.0F;
/*    */       } 
/* 60 */       FXDispatcher.INSTANCE.arcBolt(var2.field_70165_t, var2.field_70163_u, var2.field_70161_v, var3.field_70165_t, var3.field_70163_u, var3.field_70161_v, r, g, b, 0.6F);
/*    */     } 
/* 62 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/* 68 */   private Entity getEntityByID(int par1, Minecraft mc, WorldClient world) { return (par1 == mc.field_71439_g.func_145782_y()) ? mc.field_71439_g : world.func_73045_a(par1); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\fx\PacketFXWispZap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */