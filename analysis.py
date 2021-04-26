import matplotlib.pyplot as plt
import pandas as pd

df = pd.DataFrame()

labels = ['P1', 'P2', 'P3', 'P4', 'P5']
ids = ['0', '1', '2', '3', '4']
lastTime = [0.0, 0.0, 0.0, 0.0, 0.0]
eatingTimes = [0, 0, 0, 0, 0]
thinkingTimes = [0, 0, 0, 0, 0]
waitingTimes = [0, 0, 0, 0, 0]
droppingTimes = [0, 0, 0, 0, 0]
width = 0.35
lastTimeTemp = 0.0
philosopher0Times = []
j = 0

with open("DeadlockRecovery_py.txt") as file:
  for line in file:
    line = [x.strip() for x in line.split("|")]
    length = len(line)
    if length != 1 and length == 3:
      philosopher, action, time = line
      if action != "DEADLOCK":
        philosopher = str(philosopher.split(" ")[1])
        time = (float(time.split(":")[2]) - 21.364) if (float(time.split(
            ":")[2]) - 21.364) > -0.001 else (float(time.split(":")[2]) - 21.364 + 60)
        for i in ids:
          if philosopher == i and time != 0:
            j = int(i)
            lastTimeTemp = time
            time = time - lastTime[j]
            lastTime[j] = lastTimeTemp
            if action == "is hungry":
              thinkingTimes[j] = round(thinkingTimes[j] + time, 2)
            if action == "finished eating":
              eatingTimes[j] = round(eatingTimes[j] + time, 2)
            if action == "is eating":
              waitingTimes[j] = round(waitingTimes[j] + time, 2)
            if action == "is thinking":
              droppingTimes[j] = round(droppingTimes[j] + time, 2)

df = pd.DataFrame(eatingTimes, columns={'Eating'})
df['Thinking'] = pd.Series(thinkingTimes, index=df.index)
df['Waiting'] = pd.Series(waitingTimes, index=df.index)
df['Dropping'] = pd.Series(droppingTimes, index=df.index)


df.plot(kind='bar', stacked=True)
plt.title("Time Spent on Tasks (Deadlock Recovery)")
plt.xlabel("Philosopher")
plt.ylabel("Time (s)")


labels = ['P1', 'P2', 'P3', 'P4', 'P5']
ids = ['0', '1', '2', '3', '4']
lastTime = [0.0, 0.0, 0.0, 0.0, 0.0]
eatingTimes = [0, 0, 0, 0, 0]
thinkingTimes = [0, 0, 0, 0, 0]
waitingTimes = [0, 0, 0, 0, 0]
droppingTimes = [0, 0, 0, 0, 0]
width = 0.35
lastTimeTemp = 0.0
philosopher0Times = []
j = 0

with open("RandomDrop_py.txt") as file:
  for line in file:
    line = [x.strip() for x in line.split("|")]
    length = len(line)
    if length != 1 and length == 3:
      philosopher, action, time = line
      if action != "DEADLOCK":
        philosopher = str(philosopher.split(" ")[1])
        time = (float(time.split(":")[2]) - 12.193) if (float(time.split(
            ":")[2]) - 12.193) > -0.00001 else (float(time.split(":")[2]) - 12.193 + 60)
        for i in ids:
          if philosopher == i and time != 0:
            j = int(i)
            lastTimeTemp = time
            time = time - lastTime[j]
            lastTime[j] = lastTimeTemp
            if action == "is hungry":
              thinkingTimes[j] = round(thinkingTimes[j] + time, 2)
            if action == "finished eating":
              eatingTimes[j] = round(eatingTimes[j] + time, 2)
            if action == "is eating":
              waitingTimes[j] = round(waitingTimes[j] + time, 2)
            if action == "is thinking":
              droppingTimes[j] = round(droppingTimes[j] + time, 2)

df = pd.DataFrame(eatingTimes, columns={'Eating'})
df['Thinking'] = pd.Series(thinkingTimes, index=df.index)
df['Waiting'] = pd.Series(waitingTimes, index=df.index)
df['Dropping'] = pd.Series(droppingTimes, index=df.index)


df.plot(kind='bar', stacked=True)
plt.title("Time Spent on Tasks (Random Drop)")
plt.xlabel("Philosopher")
plt.ylabel("Time (s)")
