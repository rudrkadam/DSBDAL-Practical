package map_reduce;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class musicShared {
		public static void main(String [] args) throws Exception
		{
			Configuration c=new Configuration();
			String[] files=new GenericOptionsParser(c,args).getRemainingArgs();
			Path input=new Path(files[0]);
			Path output=new Path(files[1]);
			Job j=new Job(c,"Music");
			j.setJarByClass(musicShared.class);
			j.setMapperClass(MapForMusic.class);
			j.setReducerClass(ReduceForMusic.class);
			j.setOutputKeyClass(Text.class);
			j.setOutputValueClass(IntWritable.class);
			FileInputFormat.addInputPath(j, input);
			FileOutputFormat.setOutputPath(j, output);
			System.exit(j.waitForCompletion(true)?0:1);
		}
		public static class MapForMusic extends Mapper<LongWritable, Text, Text, IntWritable> {
			public void map(LongWritable key, Text value, Context con) throws IOException, InterruptedException {
			
				String lines = value.toString();
				String[] line = lines.split("\n");
				for(String words: line){
					String[] data = words.split(",");
					Text outputKey = new Text(data[1]);
					IntWritable outputValue = new IntWritable(Integer.parseInt(data[2]));
					con.write(outputKey, outputValue);
				}
			}     
	    }
		
		public static class ReduceForMusic extends Reducer<Text, IntWritable, Text, IntWritable> {
			public void reduce(Text word, Iterable<IntWritable> values, Context con) throws IOException, InterruptedException {
				int sum = 0;
				for(IntWritable value: values){
					sum+=value.get();
				}
				con.write(word, new IntWritable(sum));
			}
	    }
	}