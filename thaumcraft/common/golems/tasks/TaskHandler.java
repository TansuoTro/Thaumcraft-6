/*     */ package thaumcraft.common.golems.tasks;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.common.golems.EntityThaumcraftGolem;
/*     */ import thaumcraft.common.golems.seals.SealHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TaskHandler
/*     */ {
/*     */   static final int TASK_LIMIT = 10000;
/*  21 */   public static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Task>> tasks = new ConcurrentHashMap();
/*     */   
/*     */   public static void addTask(int dim, Task ticket) {
/*  24 */     if (!tasks.containsKey(Integer.valueOf(dim))) tasks.put(Integer.valueOf(dim), new ConcurrentHashMap()); 
/*  25 */     ConcurrentHashMap<Integer, Task> dc = (ConcurrentHashMap)tasks.get(Integer.valueOf(dim));
/*  26 */     if (dc.size() > 10000) {
/*     */       try {
/*  28 */         Iterator<Task> i = dc.values().iterator();
/*  29 */         if (i.hasNext()) {
/*  30 */           i.next();
/*  31 */           i.remove();
/*     */         } 
/*  33 */       } catch (Exception exception) {}
/*     */     }
/*  35 */     dc.put(Integer.valueOf(ticket.getId()), ticket);
/*     */   }
/*     */ 
/*     */   
/*  39 */   public static Task getTask(int dim, int id) { return (Task)getTasks(dim).get(Integer.valueOf(id)); }
/*     */ 
/*     */   
/*     */   public static ConcurrentHashMap<Integer, Task> getTasks(int dim) {
/*  43 */     if (!tasks.containsKey(Integer.valueOf(dim))) {
/*  44 */       tasks.put(Integer.valueOf(dim), new ConcurrentHashMap());
/*     */     }
/*  46 */     return (ConcurrentHashMap)tasks.get(Integer.valueOf(dim));
/*     */   }
/*     */   
/*     */   public static ArrayList<Task> getBlockTasksSorted(int dim, UUID uuid, Entity golem) {
/*  50 */     ConcurrentHashMap<Integer, Task> tickets = getTasks(dim);
/*  51 */     ArrayList<Task> out = new ArrayList<Task>();
/*     */     
/*  53 */     label27: for (Task ticket : tickets.values()) {
/*  54 */       if (ticket.isReserved() || ticket.getType() != 0 || (
/*  55 */         uuid != null && ticket.getGolemUUID() != null && !uuid.equals(ticket.getGolemUUID())))
/*  56 */         continue;  if (out.size() == 0) {
/*  57 */         out.add(ticket); continue;
/*     */       } 
/*  59 */       double d = ticket.getPos().func_177957_d(golem.field_70165_t, golem.field_70163_u, golem.field_70161_v);
/*  60 */       d -= (ticket.getPriority() * 256);
/*  61 */       for (int a = 0; a < out.size(); a++) {
/*  62 */         double d1 = ((Task)out.get(a)).getPos().func_177957_d(golem.field_70165_t, golem.field_70163_u, golem.field_70161_v);
/*  63 */         d1 -= (((Task)out.get(a)).getPriority() * 256);
/*  64 */         if (d < d1) {
/*  65 */           out.add(a, ticket);
/*     */           continue label27;
/*     */         } 
/*     */       } 
/*  69 */       out.add(ticket);
/*     */     } 
/*     */ 
/*     */     
/*  73 */     return out;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ArrayList<Task> getEntityTasksSorted(int dim, UUID uuid, Entity golem) {
/*  78 */     ConcurrentHashMap<Integer, Task> tickets = getTasks(dim);
/*  79 */     ArrayList<Task> out = new ArrayList<Task>();
/*     */ 
/*     */     
/*  82 */     label31: for (Task ticket : tickets.values()) {
/*  83 */       if (ticket.isReserved() || ticket.getType() != 1 || (
/*  84 */         uuid != null && ticket.getGolemUUID() != null && !uuid.equals(ticket.getGolemUUID())))
/*  85 */         continue;  if (ticket.getEntity() == null || (ticket.getEntity()).field_70128_L) {
/*  86 */         ticket.setSuspended(true);
/*     */         continue;
/*     */       } 
/*  89 */       if (out.size() == 0) {
/*  90 */         out.add(ticket); continue;
/*     */       } 
/*  92 */       double d = ticket.getPos().func_177957_d(golem.field_70165_t, golem.field_70163_u, golem.field_70161_v);
/*  93 */       d -= (ticket.getPriority() * 256);
/*  94 */       for (int a = 0; a < out.size(); a++) {
/*  95 */         double d1 = ((Task)out.get(a)).getPos().func_177957_d(golem.field_70165_t, golem.field_70163_u, golem.field_70161_v);
/*  96 */         d1 -= (((Task)out.get(a)).getPriority() * 256);
/*  97 */         if (d < d1) {
/*  98 */           out.add(a, ticket);
/*     */           continue label31;
/*     */         } 
/*     */       } 
/* 102 */       out.add(ticket);
/*     */     } 
/*     */ 
/*     */     
/* 106 */     return out;
/*     */   }
/*     */   
/*     */   public static void completeTask(Task task, EntityThaumcraftGolem golem) {
/* 110 */     if (task.isCompleted() || task.isSuspended())
/* 111 */       return;  ISealEntity se = SealHandler.getSealEntity(golem.field_70170_p.field_73011_w.getDimension(), task.getSealPos());
/*     */     
/* 113 */     if (se != null) {
/* 114 */       task.setCompletion(se.getSeal().onTaskCompletion(golem.field_70170_p, golem, task));
/*     */     } else {
/* 116 */       task.setCompletion(true);
/*     */     } 
/*     */   }
/*     */   public static void clearSuspendedOrExpiredTasks(World world) {
/* 120 */     ConcurrentHashMap<Integer, Task> tickets = getTasks(world.field_73011_w.getDimension());
/* 121 */     ConcurrentHashMap<Integer, Task> temp = new ConcurrentHashMap<Integer, Task>();
/* 122 */     for (Task ticket : tickets.values()) {
/* 123 */       if (!ticket.isSuspended() && ticket.getLifespan() > 0L) {
/* 124 */         ticket.setLifespan((short)(int)(ticket.getLifespan() - 1L));
/* 125 */         temp.put(Integer.valueOf(ticket.getId()), ticket); continue;
/*     */       } 
/* 127 */       ISealEntity sEnt = SealHandler.getSealEntity(world.field_73011_w.getDimension(), ticket.getSealPos());
/* 128 */       if (sEnt != null) {
/* 129 */         sEnt.getSeal().onTaskSuspension(world, ticket);
/*     */       }
/*     */     } 
/* 132 */     tasks.put(Integer.valueOf(world.field_73011_w.getDimension()), temp);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\tasks\TaskHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */