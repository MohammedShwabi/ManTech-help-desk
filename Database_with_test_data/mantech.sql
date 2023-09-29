-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 29, 2023 at 03:55 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mantech`
--
create database if not exists `ManTech`;
use `ManTech`;

-- --------------------------------------------------------

--
-- Table structure for table `blogs`
--

CREATE TABLE `blogs` (
  `id` int(11) NOT NULL,
  `photo` varchar(30) NOT NULL,
  `title` varchar(60) NOT NULL,
  `description` text NOT NULL,
  `blog` text NOT NULL DEFAULT CURRENT_DATE(),
  `created_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `blogs`
--

INSERT INTO `blogs` (`id`, `photo`, `title`, `description`, `blog`, `created_at`) VALUES
(1, 'selfDriving.png', 'The Future of Self-Driving Cars', 'A Glimpse into the World of Autonomous Vehicles', 'Self-driving cars, also known as autonomous vehicles, have captured the imagination of the world and hold the potential to revolutionize the way we travel. These vehicles are equipped with advanced sensors, machine learning algorithms, and cutting-edge technology, enabling them to navigate roads and make decisions without human intervention. As technology continues to advance, the future of self-driving cars is becoming increasingly promising.\r\n\r\n    The development of self-driving cars has been driven by tech giants such as Google’s Waymo, Tesla, and traditional automakers like Ford and General Motors. These companies have invested billions of dollars in research and development to make autonomous vehicles safer and more reliable. As a result, we are now seeing self-driving cars on the streets, undergoing testing and gradually becoming a part of our daily lives.\r\n\r\n    One of the key benefits of self-driving cars is their potential to reduce accidents and save lives. Human error is a leading cause of accidents on the road, and autonomous vehicles have the ability to eliminate this factor. They can react faster than humans to unexpected situations, follow traffic rules consistently, and communicate with other vehicles on the road, making driving safer for everyone.\r\n\r\n    In addition to safety, self-driving cars have the potential to revolutionize transportation. Imagine a world where you can summon a self-driving car with a smartphone app, and it arrives at your doorstep to take you to your destination. You can relax, work, or even sleep during your commute, as the car handles the driving. This could significantly reduce traffic congestion and the need for parking spaces in urban areas.\r\n\r\n    However, there are still challenges to overcome. The technology powering self-driving cars must continue to improve to handle complex scenarios, adverse weather conditions, and edge cases. Legal and regulatory frameworks also need to be established to ensure the safe integration of autonomous vehicles into existing traffic.\r\n\r\n    Another important aspect of the self-driving car revolution is the impact on the job market. While autonomous vehicles have the potential to create new job opportunities in technology and maintenance, they could also lead to the displacement of traditional driving jobs, such as truck drivers and taxi drivers.\r\n\r\n    In conclusion, the future of self-driving cars is promising but comes with its share of challenges. As technology continues to advance, we can expect to see more autonomous vehicles on the road, reshaping the way we think about transportation and offering new opportunities for safety, efficiency, and convenience.', '2023-09-19'),
(2, 'roll_ai.jpg', 'The Role of Artificial Intelligence in Healthcare', 'Exploring AI’s Impact on Medical Diagnosis and Treatment', 'Artificial Intelligence (AI) is rapidly transforming the healthcare industry, revolutionizing the way medical professionals diagnose, treat, and manage patients. The integration of AI into healthcare systems has the potential to improve patient outcomes, increase efficiency, and reduce costs.\r\n\r\n    One of the most promising applications of AI in healthcare is medical image analysis. AI algorithms can analyze medical images, such as X-rays, MRIs, and CT scans, with incredible accuracy. This enables early detection of diseases and conditions, leading to faster and more effective treatment. AI can also assist radiologists and pathologists in their diagnoses, reducing the likelihood of human error.\r\n\r\n    AI-powered chatbots and virtual assistants are becoming valuable tools in healthcare. They can provide patients with instant access to medical information, answer questions, and schedule appointments. Additionally, AI-driven predictive analytics can help healthcare providers identify patients at risk of developing certain medical conditions and tailor preventive care plans.\r\n\r\n    Drug discovery and development are time-consuming and expensive processes. AI is being used to analyze massive datasets and identify potential drug candidates more efficiently. This can significantly speed up the drug development pipeline and lead to the discovery of new treatments for various diseases.\r\n\r\n    However, the adoption of AI in healthcare is not without challenges. Data privacy and security are paramount concerns, as healthcare data is highly sensitive. Ensuring the ethical use of AI in medical decision-making is also a crucial consideration.\r\n\r\n    In conclusion, AI is poised to play a transformative role in healthcare, offering solutions to longstanding challenges and improving patient care. As AI technologies continue to evolve, we can expect to see even greater advancements in medical diagnosis, treatment, and healthcare delivery.', '2023-09-18'),
(3, 'green_tech.jpg', 'The Green Revolution: Sustainable Technology Trends', 'Exploring Innovations for a Greener Future', 'The global shift towards sustainability has led to an increased focus on green technology innovations. These innovations aim to address pressing environmental challenges and reduce our carbon footprint. From renewable energy solutions to eco-friendly transportation, sustainable technology trends are shaping our path to a greener future.\r\n\r\n    Renewable energy sources, such as solar and wind power, have gained widespread adoption. Advances in solar panel efficiency and energy storage systems are making clean energy more accessible and affordable. Additionally, smart grid technologies are optimizing energy distribution, reducing waste, and improving energy conservation.\r\n\r\n    Electric vehicles (EVs) are revolutionizing the automotive industry. EVs produce zero emissions, helping combat air pollution and reduce our reliance on fossil fuels. The development of EV charging infrastructure is crucial for the widespread adoption of electric vehicles.\r\n\r\n    Sustainable agriculture practices are leveraging technology to improve crop yields while minimizing environmental impact. Precision farming techniques, enabled by sensors and data analytics, allow farmers to optimize resource usage and reduce chemical inputs.\r\n\r\n    The circular economy concept promotes the reuse and recycling of products and materials. Technology is playing a vital role in enabling closed-loop systems where products are designed for longevity and recyclability.\r\n\r\n    The Internet of Things (IoT) is facilitating smart, energy-efficient homes and buildings. Connected devices can monitor and control energy usage, lighting, and heating, leading to significant energy savings.\r\n\r\n    Despite these positive developments, challenges remain. The transition to sustainable technologies requires significant investment, and policy support is essential to accelerate adoption. Additionally, addressing electronic waste (e-waste) and responsible disposal of outdated technologies are critical considerations.\r\n\r\n    In conclusion, sustainable technology trends are driving positive changes in various sectors, promoting environmental stewardship and sustainable practices. As these trends continue to evolve, they hold the potential to reshape industries and contribute to a more sustainable and eco-friendly future.', '2023-09-17'),
(4, 'evolution-of-operating.webp', 'The Evolution of Operating Systems', 'From Command Lines to Graphical User Interfaces (GUIs)', 'Operating systems (OS) are the backbone of modern computing, serving as a bridge between hardware and software. Over the decades, operating systems have evolved significantly, shaping the user experience and the capabilities of computing devices.\r\n\r\n    The earliest operating systems were command-line interfaces, where users interacted with the computer by typing text-based commands. These systems were primarily used by computer experts and required a deep understanding of the underlying hardware.\r\n\r\n    The introduction of graphical user interfaces (GUIs) in the 1980s marked a major milestone in OS development. GUIs, pioneered by Apple’s Macintosh and later Microsoft’s Windows, made computers more user-friendly and accessible to a broader audience. Users could now interact with icons, menus, and windows, eliminating the need for command-line knowledge.\r\n\r\n    In the 1990s, the rise of the internet brought about network-capable operating systems. These OSs facilitated internet connectivity, email communication, and web browsing. Microsoft’s Windows 95 was a notable example, with built-in networking support.\r\n\r\n    Mobile operating systems, such as Android and iOS, have transformed the way we use smartphones and tablets. These OSs prioritize touch-friendly interfaces and app ecosystems, allowing users to perform a wide range of tasks on mobile devices.\r\n\r\n    The evolution of operating systems continues with the advent of cloud computing. Cloud OSs enable seamless access to data and applications from anywhere with an internet connection. They prioritize data security and collaboration.\r\n\r\n    While operating systems have come a long way in terms of usability and functionality, they also face challenges related to security and privacy. Cybersecurity threats and the protection of user data are top priorities for modern OS developers.\r\n\r\n    In conclusion, the evolution of operating systems has been characterized by a shift from complex command-line interfaces to user-friendly graphical interfaces and a focus on connectivity and mobility. The future of operating systems promises even greater integration with cloud services and enhanced security features.', '2023-09-16'),
(5, 'open_source.png', 'The Impact of Open Source Software', 'A Look at the Growth and Influence of Open Source Projects', 'Open source software (OSS) has become a fundamental part of the software industry, powering a wide range of applications and services. OSS is characterized by its open and collaborative development model, where source code is made available to the public for inspection, modification, and distribution.\r\n\r\n    The roots of open source software can be traced back to the Free Software Foundation and the GNU project, which advocated for software freedom and open access to source code. The open source movement gained momentum with the release of the Linux operating system, an open source alternative to proprietary operating systems.\r\n\r\n    Today, open source software is ubiquitous. It is the foundation of the internet, with projects like the Apache web server, the Linux kernel, and the MySQL database server powering a significant portion of web services and websites.\r\n\r\n    The impact of open source software extends beyond the technology sector. It has influenced business models, fostering the growth of companies that offer support, consulting, and customization services for open source solutions.\r\n\r\n    Open source software has democratized software development, allowing individuals and organizations to collaborate on projects of all sizes. The open source community is known for its inclusivity and the diversity of contributors from around the world.\r\n\r\n    While open source software offers numerous advantages, it also faces challenges. Sustainability and funding are ongoing concerns for many open source projects. Maintaining and updating software requires resources, and finding sustainable funding models is essential to the long-term success of open source projects.\r\n\r\n    In conclusion, open source software has had a profound impact on the software industry, promoting transparency, collaboration, and innovation. As open source projects continue to evolve and grow, they will play an increasingly important role in shaping the future of technology.', '2023-09-15'),
(6, 'rise-of-devops.png', 'The Rise of DevOps in Software Development', 'Exploring the Intersection of Development and Operations', 'DevOps, a combination of \"development\" and \"operations,\" is a set of practices and principles that aim to bridge the gap between software development and IT operations. DevOps promotes collaboration, automation, and continuous delivery, leading to faster and more reliable software development processes.\r\n\r\n    Traditionally, software development and operations were separate silos within organizations, often working in isolation. This led to inefficiencies, delays in software deployment, and a lack of alignment between development and operational teams.\r\n\r\n    DevOps seeks to break down these barriers by fostering a culture of collaboration and shared responsibility. It emphasizes automation of repetitive tasks, such as code integration, testing, and deployment, through the use of tools and technologies.\r\n\r\n    Continuous integration (CI) and continuous delivery (CD) are key components of DevOps. CI involves automatically integrating code changes into a shared repository and running automated tests to detect issues early in the development cycle. CD extends CI by automating the deployment of code to production environments, ensuring a streamlined and error-free release process.\r\n\r\n    DevOps practices have gained popularity due to their ability to accelerate software development and improve product quality. By automating manual tasks and implementing robust testing procedures, DevOps teams can deliver software updates more frequently and with greater confidence.\r\n\r\n    Additionally, the adoption of containerization and container orchestration technologies, such as Docker and Kubernetes, has further enhanced DevOps capabilities. Containers enable consistent and reproducible software deployments across different environments.\r\n\r\n    While DevOps offers numerous benefits, it also requires organizations to undergo cultural and procedural changes. Collaboration and communication between development and operations teams are critical, and there may be resistance to change within traditional organizations.\r\n\r\n    In conclusion, DevOps has become a driving force in modern software development, enabling organizations to deliver software faster, with higher quality, and improved reliability. As technology continues to evolve, DevOps practices will play a crucial role in shaping the future of software development.', '2023-09-14'),
(7, 'iot.png', 'The Internet of Things (IoT) and Its Impact on Daily Life', 'Exploring the Growing Connected Ecosystem', 'The Internet of Things (IoT) refers to the network of interconnected physical devices, vehicles, appliances, and other objects that collect and exchange data. These devices are embedded with sensors, software, and connectivity, enabling them to communicate and interact with each other. IoT is rapidly reshaping various aspects of daily life.\r\n\r\n    IoT devices are becoming increasingly common in homes, offering convenience and automation. Smart thermostats can adjust heating and cooling based on occupancy and weather conditions, saving energy and reducing utility bills. Smart speakers and voice assistants provide hands-free control over various household tasks.\r\n\r\n    Wearable devices, such as fitness trackers and smartwatches, have gained popularity for monitoring health and fitness. They can track steps, heart rate, sleep patterns, and more, empowering individuals to make informed health decisions.\r\n\r\n    In agriculture, IoT sensors and drones are revolutionizing farming practices. Farmers can monitor soil conditions, crop health, and irrigation remotely, optimizing resource usage and increasing crop yields.\r\n\r\n    IoT plays a critical role in transportation and logistics. Connected vehicles can transmit data about their performance, location, and maintenance needs, leading to improved safety and efficiency. Smart traffic management systems can reduce congestion and improve traffic flow.\r\n\r\n    Industrial IoT (IIoT) is transforming manufacturing and production processes. Smart factories leverage IoT sensors and data analytics to enhance automation, reduce downtime, and improve product quality.\r\n\r\n    While IoT offers numerous benefits, it also raises concerns about data privacy and security. The sheer volume of data generated by IoT devices requires robust cybersecurity measures to protect sensitive information.\r\n\r\n    In conclusion, the Internet of Things is reshaping daily life by connecting and automating various aspects of our environment. As IoT technologies continue to evolve, they will play an increasingly integral role in how we live, work, and interact with the world.', '2023-09-13'),
(8, 'evolution-of-5g.webp', 'The Evolution of 5G Technology', 'A Look at the Journey to Faster and More Connected Networks', 'The rollout of 5G technology has been a game-changer in the world of telecommunications. 5G, which stands for fifth generation, represents the latest and fastest wireless network standard. It promises significantly faster data speeds, lower latency, and the ability to connect a massive number of devices simultaneously.\r\n\r\n    The journey to 5G began with the development of earlier wireless generations, including 1G, 2G, 3G, and 4G. Each generation brought improvements in network speed and capabilities. 4G, in particular, paved the way for mobile internet access and app-driven ecosystems.\r\n\r\n    5G technology builds upon the foundation of 4G but introduces several key enhancements. It utilizes higher-frequency radio waves, known as millimeter waves, to transmit data at incredibly high speeds. This enables applications such as augmented reality (AR), virtual reality (VR), and ultra-high-definition video streaming.\r\n\r\n    The low latency of 5G is critical for real-time applications. It is a game-changer for industries like healthcare, where remote surgeries and telemedicine benefit from instant communication. Autonomous vehicles also rely on low latency to make split-second decisions.\r\n\r\n    The Internet of Things (IoT) benefits greatly from 5G connectivity. The ability to connect a massive number of IoT devices simultaneously is essential for smart cities, industrial automation, and connected infrastructure.\r\n\r\n    5G technology is not without challenges. It requires a dense network of small cell towers due to its limited coverage area, and there are concerns about its impact on health and the environment.\r\n\r\n    In conclusion, 5G technology represents a significant leap forward in wireless communication. It is poised to transform industries, enhance connectivity, and enable innovative applications that were previously unimaginable.', '2023-09-12'),
(9, 'problem_solve.jpg', 'The Art of Problem Solving in Programming', 'Strategies for Tackling Complex Programming Challenges', 'Problem-solving is at the heart of programming. Whether you’re a beginner or an experienced developer, you will encounter complex challenges that require creative solutions. Developing effective problem-solving skills is essential for success in programming.\r\n\r\n    Break the problem down into smaller steps: When faced with a complex problem, it can be overwhelming to tackle it all at once. Break it down into smaller, more manageable steps. Start by identifying the essential components and functions that need to be implemented.\r\n\r\n    Pseudocode and planning: Before writing actual code, use pseudocode or flowcharts to outline your algorithm. This step helps you visualize the logic and structure of your solution. It’s easier to spot potential issues and make improvements in a pseudocode phase.\r\n\r\n    Use data structures and algorithms: Choose the right data structures and algorithms for the task at hand. Understanding the strengths and weaknesses of different data structures, such as arrays, linked lists, and hash tables, is crucial. Similarly, knowing when to use sorting algorithms or search algorithms is essential.\r\n\r\n    Test incrementally: Avoid writing all the code at once and hoping for the best. Test your code incrementally by implementing and testing individual components as you go. This approach helps you catch bugs early and ensures that each part of your solution works correctly.\r\n\r\n    Debugging skills: Debugging is a fundamental skill in programming. Learn to use debugging tools and techniques effectively. Understand error messages and use them as clues to identify and fix issues in your code.\r\n\r\n    Seek help and collaborate: Don’t hesitate to seek help when you’re stuck. Online communities, forums, and developer colleagues can provide valuable insights and solutions. Collaborating with others on challenging problems can lead to fresh perspectives and innovative solutions.\r\n\r\n    Practice, practice, practice: Problem-solving skills improve with practice. Solve a variety of programming challenges, participate in coding competitions, and work on personal projects. The more problems you tackle, the more strategies and techniques you’ll develop.\r\n\r\n    In conclusion, problem-solving is a core skill in programming, and it’s essential to approach challenges with a systematic and logical mindset. By breaking down problems, planning, using the right tools, and seeking help when needed, you can become a more effective and confident programmer.', '2023-09-11'),
(10, 'low_code.webp', 'The Rise of Low-Code and No-Code Development', 'Democratizing Software Development for Non-Developers', 'Low-code and no-code development platforms are changing the landscape of software development, making it accessible to a broader audience, including business users and non-developers. These platforms empower users to create software applications with minimal coding skills, significantly reducing development time and costs.\r\n\r\n    Low-code platforms provide a visual interface and pre-built components that users can drag and drop to design applications. These platforms still require some coding but simplify the process, allowing users to focus on high-level logic and functionality.\r\n\r\n    No-code platforms, on the other hand, require no coding at all. Users can create applications using visual interfaces and configuration options. No-code platforms are ideal for automating tasks, building simple applications, and prototyping.\r\n\r\n    The democratization of software development through low-code and no-code platforms has several advantages. It enables business users to take a more active role in software creation, reducing the reliance on IT departments. It accelerates application development, allowing organizations to respond quickly to changing business needs.\r\n\r\n    These platforms are particularly valuable for prototyping and proof-of-concept projects. They enable users to quickly test ideas and validate concepts without extensive coding.\r\n\r\n    However, low-code and no-code platforms also have limitations. They may not be suitable for complex, highly customized applications that require intricate logic and integration with external systems. Security and scalability considerations are essential when using these platforms.\r\n\r\n    In conclusion, low-code and no-code development platforms are changing the way software is created and empowering a broader range of users to participate in application development. While they have their limitations, they offer significant benefits in terms of speed and accessibility.', '2023-09-10'),
(11, 'code_review.png', 'The Art of Code Review', 'Best Practices for Collaborative Code Review', 'Code review is a crucial part of the software development process. It involves team members examining and evaluating code changes made by their peers. Effective code review improves code quality, detects issues early, and fosters collaboration among team members.\r\n\r\n    Set clear objectives: Define the goals and expectations of the code review process. Are you looking for bugs, adherence to coding standards, or architectural improvements? Clear objectives help reviewers focus their feedback.\r\n\r\n    Keep reviews small: Large code reviews can be overwhelming and time-consuming. Aim for smaller, more focused reviews that are easier to manage. Smaller changes are also less likely to introduce complex issues.\r\n\r\n    Use a checklist: Consider using a checklist of common issues and best practices. This can help reviewers ensure that they cover all necessary aspects of the code, such as error handling, testing, and documentation.\r\n\r\n    Be constructive and respectful: Code review is a collaborative process, not a personal critique. Provide feedback in a constructive and respectful manner. Focus on the code and its quality, not on the author.\r\n\r\n    Prioritize feedback: Not all feedback is equally important. Prioritize feedback based on its impact on code quality and project goals. Address critical issues first and work your way down the list.\r\n\r\n    Encourage discussions: Code review should be a dialogue between the author and reviewers. Encourage discussions to clarify design decisions, share knowledge, and reach a consensus on the best approach.\r\n\r\n    Use code review tools: Leveraging code review tools and platforms can streamline the process. These tools often provide features for tracking changes, commenting, and managing the review workflow.\r\n\r\n    Automate checks: Consider automating code quality checks, such as static analysis and unit tests. Automated tools can catch common issues early, leaving more time for reviewers to focus on higher-level concerns.\r\n\r\n    In conclusion, code review is an essential practice for maintaining code quality and fostering collaboration within development teams. By following best practices and keeping the process constructive, teams can benefit from more reliable and maintainable software.', '2023-09-09'),
(12, 'space_explore.jpg', 'The Future of Space Exploration', 'New Frontiers, Ambitious Missions, and Beyond', 'Space exploration has always captivated human imagination, and the future promises even more ambitious and groundbreaking missions. Advancements in technology, international collaboration, and private sector involvement are driving the next era of space exploration.\r\n\r\n    Mars exploration is a focal point of future missions. Multiple space agencies and private companies are planning crewed and uncrewed missions to the Red Planet. These missions aim to search for signs of past or present life, study Martian geology, and pave the way for potential human colonization.\r\n\r\n    Lunar exploration is also experiencing a resurgence. NASA’s Artemis program aims to return astronauts to the Moon, including the first woman and the next man, by the mid-2020s. Establishing a sustainable presence on the Moon could serve as a stepping stone for future missions to Mars and beyond.\r\n\r\n    Commercial space travel is on the horizon. Private companies like SpaceX, Blue Origin, and Virgin Galactic are working to make space travel more accessible to civilians. Suborbital flights and potential space tourism are becoming a reality.\r\n\r\n    International cooperation in space is expanding. The International Space Station (ISS) continues to serve as a symbol of collaboration among multiple countries. Future projects, such as the Lunar Gateway, involve even more countries in deep space exploration.\r\n\r\n    Beyond our solar system, efforts to search for extraterrestrial life are ongoing. Telescopes like the James Webb Space Telescope (JWST) will provide unprecedented insights into distant galaxies and potentially habitable exoplanets.\r\n\r\n    However, space exploration also faces challenges. Funding, environmental concerns, and the ethical implications of human expansion into space are important considerations.\r\n\r\n    In conclusion, the future of space exploration is filled with promise and excitement. Humanity is on the brink of new discoveries, ambitious missions, and the expansion of our presence beyond Earth.', '2023-09-08'),
(13, 'game_evolve.jpg', 'The Evolution of Video Game Graphics', 'From Pixels to Photorealism', 'The evolution of video game graphics is a testament to the rapid advancements in technology. From the early days of pixelated sprites to the breathtaking photorealism of modern games, graphics have played a crucial role in the gaming experience.\r\n\r\n    Early video games, such as Pong and Pac-Man, featured simple, blocky graphics due to the limitations of hardware. The pixel art style was born out of necessity, and it became iconic in its own right.\r\n\r\n    The introduction of 3D graphics in the 1990s marked a significant milestone. Games like Doom and Quake revolutionized the industry by rendering environments in three dimensions, allowing for more immersive experiences.\r\n\r\n    The late 1990s and early 2000s saw the emergence of realistic 3D graphics in games like Half-Life and Final Fantasy VII. These games pushed the boundaries of what was possible in terms of character models, textures, and environments.\r\n\r\n    The 2010s brought about a new era of gaming graphics with the advent of high-definition (HD) and 4K resolutions. Games like The Witcher 3 and Red Dead Redemption 2 set new standards for visual fidelity and realism.\r\n\r\n    Ray tracing technology, which simulates the behavior of light in real-time, has become a game-changer for graphics. It adds a level of realism previously unseen in video games, with realistic reflections, shadows, and lighting effects.\r\n\r\n    Virtual reality (VR) has further elevated the gaming experience by immersing players in fully 3D environments. VR requires even more advanced graphics to maintain immersion and prevent motion sickness.\r\n\r\n    The future of video game graphics is exciting. Advancements in hardware, such as faster GPUs and ray tracing support, will continue to push the boundaries of realism. Emerging technologies like real-time AI-driven graphics and photogrammetry will further enhance visual quality.\r\n\r\n    In conclusion, the evolution of video game graphics has been a journey from simple pixel art to breathtaking photorealism. Graphics have played a pivotal role in the evolution of gaming, enhancing immersion and storytelling.', '2023-09-07'),
(14, 'art_visual.png', 'The Art of Data Visualization', 'Effective Techniques for Presenting Complex Data', 'Data visualization is a powerful tool for making sense of complex information and communicating insights. Whether you’re analyzing business data, scientific research, or social trends, effective data visualization can make your findings more accessible and impactful.\r\n\r\n    Choose the right chart type: Different types of data are best represented by specific chart types. Bar charts are ideal for comparing values, line charts show trends over time, and pie charts display parts of a whole. Select the chart that best suits your data.\r\n\r\n    Keep it simple: Avoid clutter and unnecessary complexity in your visualizations. Focus on the key insights you want to convey. Use clear labels and minimal color to enhance readability.\r\n\r\n    Use storytelling: Contextualize your data by telling a story. Explain the significance of your findings and guide the viewer through the visualization. Use annotations and captions to highlight important points.\r\n\r\n    Interactivity: When appropriate, add interactivity to your visualizations. Interactive elements like tooltips and filters allow users to explore data in more detail, providing a richer experience.\r\n\r\n    Color choices: Be mindful of color choices. Use color to emphasize specific data points or categories, but avoid using too many colors, which can lead to confusion. Consider colorblind-friendly palettes.\r\n\r\n    Data integrity: Ensure the accuracy and integrity of your data. Misleading visualizations can lead to incorrect conclusions. Double-check your data sources and calculations.\r\n\r\n    Experiment and iterate: Data visualization is both an art and a science. Experiment with different visualization techniques and layouts. Seek feedback and iterate on your designs to improve clarity and impact.\r\n\r\n    Accessibility: Make your visualizations accessible to all users. Provide alternative text for images, use scalable vector graphics (SVGs), and ensure compatibility with screen readers.\r\n\r\n    In conclusion, data visualization is a valuable skill for conveying information effectively. By following best practices and considering the needs of your audience, you can create impactful visualizations that enhance understanding and decision-making.', '2023-09-06'),
(15, 'quantum_computing.png', 'The Future of Quantum Computing', 'Unleashing the Power of Quantum Mechanics', 'Quantum computing is on the cusp of revolutionizing the world of computation. Unlike classical computers that use bits to represent information as either 0 or 1, quantum computers use quantum bits or qubits, which can exist in multiple states simultaneously. This fundamental difference holds the promise of solving complex problems exponentially faster than classical computers.\r\n\r\n    Quantum supremacy, the point at which quantum computers surpass the computational capabilities of the most advanced classical supercomputers, has been achieved in recent years. Google’s quantum computer, Sycamore, demonstrated this milestone by performing a task in minutes that would take traditional supercomputers thousands of years.\r\n\r\n    Quantum computing has the potential to impact various fields. In cryptography, it could break existing encryption methods while also enabling more secure quantum encryption. Drug discovery and material science could benefit from quantum simulations that model molecular interactions with unprecedented accuracy.\r\n\r\n    Optimization problems, such as route planning and resource allocation, stand to benefit from quantum computing’s ability to explore vast solution spaces quickly. Machine learning algorithms could be enhanced by quantum processors, leading to more advanced artificial intelligence.\r\n\r\n    However, building practical and scalable quantum computers remains a significant challenge. Quantum bits are fragile and prone to errors due to decoherence. Quantum error correction is an active area of research to address these challenges.\r\n\r\n    The future of quantum computing is bright but uncertain. It will likely involve hybrid approaches that combine classical and quantum processors to leverage the strengths of both. Quantum cloud services are also emerging, allowing researchers and developers to access quantum computing resources remotely.\r\n\r\n    In conclusion, quantum computing represents a paradigm shift in computing capabilities with the potential to transform industries and solve problems previously considered intractable. As research and development continue, we can expect to see quantum computing’s impact grow.', '2023-09-05');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Hardware'),
(2, 'Software');

-- --------------------------------------------------------

--
-- Stand-in structure for view `complaint_by_month`
-- (See below for the actual view)
--
CREATE TABLE `complaint_by_month` (
`id` int(11)
,`priority` enum('low','medium','high')
,`created_date` datetime
,`closed_date` datetime
,`employee` varchar(50)
,`cat_id` int(11)
,`category` varchar(20)
,`dep_id` int(11)
,`department` varchar(50)
,`technician` varchar(50)
,`formatted_month` varchar(7)
,`week_number` int(6)
,`date` date
);

-- --------------------------------------------------------

--
-- Table structure for table `compliants`
--

CREATE TABLE `compliants` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `photo` varchar(30) DEFAULT NULL,
  `priority` enum('low','medium','high') NOT NULL DEFAULT 'medium',
  `status` enum('waiting','pending','closed') NOT NULL DEFAULT 'waiting',
  `resend` tinyint(1) NOT NULL DEFAULT 0,
  `created_date` datetime NOT NULL,
  `closed_date` datetime DEFAULT NULL,
  `emp_id` int(11) NOT NULL,
  `cat_id` int(11) NOT NULL,
  `tech_id` int(11) DEFAULT NULL,
  `answer` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `compliants`
--

INSERT INTO `compliants` (`id`, `title`, `description`, `photo`, `priority`, `status`, `resend`, `created_date`, `closed_date`, `emp_id`, `cat_id`, `tech_id`, `answer`) VALUES
(1, 'Printer Not Printing', 'The office printer is not printing any documents. We need it fixed urgently.', 'printer_issue.jpg', 'high', 'closed', 0, '2020-01-22 23:38:14', '2020-01-24 23:38:14', 2, 1, 12, 'Assigned to technician. Technician will check and repair the printer.'),
(2, 'Email Login Issue', 'I am unable to log in to my email account. It keeps showing an error message.', 'email_issue.png', 'medium', 'closed', 0, '2020-02-21 23:38:14', '2020-02-24 23:38:14', 3, 2, 13, 'Email server issue detected. Investigating the problem.'),
(3, 'Computer Won’t Boot', 'My computer won’t start up. It gets stuck on the loading screen.', 'computer_issue.jpg', 'high', 'closed', 0, '2020-03-23 23:38:14', '2020-03-24 23:38:14', 4, 1, 14, 'Diagnosing hardware issue. Replacing faulty components.'),
(4, 'Software Crashing', 'The software we use for accounting keeps crashing. We can’t do our work.', 'software_issue.png', 'medium', 'closed', 0, '2021-04-20 23:38:14', '2021-04-24 23:38:14', 5, 2, 15, 'Updating software to the latest version to resolve crashes.'),
(5, 'Network Connection Problem', 'Our office network keeps dropping, causing disruptions in work.', 'network_issue.jpg', 'high', 'closed', 0, '2021-05-22 23:38:14', '2021-05-24 23:38:14', 6, 1, 16, 'Identified network router issue. Rebooting router and optimizing settings.'),
(6, 'Slow Internet Speed', 'The internet speed is painfully slow. It’s affecting our productivity.', 'internet_issue.jpg', 'low', 'closed', 0, '2021-06-19 23:38:14', '2021-06-24 23:38:14', 7, 2, 17, 'Investigating internet service provider issue. Contacting ISP for resolution.'),
(7, 'Printer Jammed', 'The printer is jammed with paper. It needs to be cleared and fixed.', 'printer_issue.jpg', 'medium', 'closed', 0, '2022-07-22 23:38:14', '2022-07-24 23:38:14', 8, 1, 18, 'Printer cleared and serviced. It should work properly now.'),
(8, 'Email Attachment Issue', 'I can’t attach files to my emails. The attachment button is not working.', 'email_issue.png', 'high', 'closed', 0, '2022-08-21 23:38:14', '2022-08-24 23:38:14', 9, 2, 19, 'Resolved email client issue. Attachment feature is working.'),
(9, 'Computer Virus Detected', 'My computer is showing signs of a virus. Need it checked and cleaned.', 'computer_issue.jpg', 'low', 'closed', 0, '2022-10-23 23:38:14', '2022-10-24 23:38:14', 10, 1, 20, 'Running antivirus scan and removing detected threats.'),
(10, 'Software License Expired', 'The license for our critical software has expired. We need it renewed.', 'software_issue.png', 'medium', 'closed', 0, '2023-09-20 23:38:14', '2023-09-24 23:38:14', 11, 2, 21, 'Renewed software license. Software is fully functional now.'),
(11, 'Printer Not Responding', 'The printer is not responding to any print commands. Please fix it.', 'printer_issue.jpg', 'high', 'closed', 0, '2023-09-22 23:38:14', '2023-09-24 23:38:14', 2, 1, 12, 'Printer driver issue resolved. Printer is operational.'),
(12, 'Email Server Down', 'The email server seems to be down. We can’t send or receive emails.', 'email_issue.png', 'medium', 'closed', 0, '2023-09-21 23:38:14', '2023-09-24 23:38:14', 3, 2, 13, 'Email server restarted. Email services are back online.'),
(13, 'Computer Overheating', 'My computer is overheating and shutting down. It needs cooling.', 'computer_issue.jpg', 'low', 'closed', 0, '2023-09-23 23:38:14', '2023-09-24 23:38:14', 4, 1, 14, 'Cleaned computer’s cooling system. Temperature issue resolved.'),
(14, 'Software Update Required', 'We need an urgent update for our software to fix bugs and glitches.', 'software_issue.png', 'medium', 'closed', 0, '2023-09-20 23:38:14', '2023-09-24 23:38:14', 5, 2, 15, 'Software updated to the latest version. Bugs fixed.'),
(15, 'Network Router Failure', 'Our network router has stopped working. We need a replacement.', 'network_issue.jpg', 'high', 'closed', 0, '2023-09-22 23:38:14', '2023-09-24 23:38:14', 6, 1, 16, 'Replaced faulty router. Network is stable now.'),
(16, 'Slow Wi-Fi Connection', 'The Wi-Fi connection is extremely slow. It needs to be faster.', 'internet_issue.jpg', 'low', 'pending', 0, '2023-09-19 23:38:14', NULL, 7, 2, 17, NULL),
(17, 'Printer Out of Ink', 'The printer is out of ink. We need a new cartridge.', 'printer_issue.jpg', 'medium', 'pending', 0, '2023-09-22 23:38:14', NULL, 8, 1, 18, NULL),
(18, 'Email Filter Issue', 'Some important emails are going to the spam folder. Please fix the filter.', 'email_issue.png', 'high', 'pending', 0, '2023-09-21 23:38:14', NULL, 9, 2, 19, NULL),
(19, 'Computer Freezing Frequently', 'My computer keeps freezing, making it impossible to work.', 'computer_issue.jpg', 'low', 'waiting', 0, '2023-09-23 23:38:14', NULL, 1, 2, NULL, NULL),
(20, 'Software Compatibility Issue', 'Our software is not compatible with the latest OS. We need an update.', 'software_issue.png', 'medium', 'waiting', 1, '2023-09-20 23:38:14', NULL, 2, 2, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE `departments` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `departments`
--

INSERT INTO `departments` (`id`, `name`) VALUES
(1, 'Educational services'),
(2, 'Management services'),
(3, 'Learning services'),
(4, 'Internal system'),
(5, 'Human resource'),
(6, 'Allied system');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `id` int(11) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `gender` enum('Male','Female') NOT NULL,
  `activated` tinyint(1) NOT NULL DEFAULT 0,
  `email` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL,
  `phone_no` varchar(20) NOT NULL,
  `photo` varchar(30) DEFAULT NULL,
  `dep_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id`, `full_name`, `gender`, `activated`, `email`, `password`, `phone_no`, `photo`, `dep_id`) VALUES
(1, 'Hesham Noaman', 'Male', 1, 'admin@gmail.com', 'admin', '01236547890', 'avatar.svg', NULL),
(2, 'Jane Smith', 'Female', 1, 'janesmith@example.com', 'password2', '555-2222', 'avatar.svg', 2),
(3, 'Mike Johnson', 'Male', 1, 'mikejohnson@example.com', 'password3', '555-3333', 'avatar.svg', 3),
(4, 'Emily Brown', 'Female', 1, 'emilybrown@example.com', 'password4', '555-4444', 'avatar.svg', 4),
(5, 'David Wilson', 'Male', 1, 'davidwilson@example.com', 'password5', '555-5555', 'avatar.svg', 5),
(6, 'Sarah Lee', 'Female', 1, 'sarahlee@example.com', 'password6', '555-6666', 'avatar.svg', 1),
(7, 'Michael Chen', 'Male', 1, 'michaelchen@example.com', 'password7', '555-7777', 'avatar.svg', 2),
(8, 'Linda Davis', 'Female', 1, 'lindadavis@example.com', 'password8', '555-8888', 'avatar.svg', 3),
(9, 'Daniel Kim', 'Male', 1, 'danielkim@example.com', 'password9', '555-9999', 'avatar.svg', 4),
(10, 'Lisa Patel', 'Female', 1, 'lisapatel@example.com', 'password10', '555-1010', 'avatar.svg', 5),
(11, 'John Doe', 'Male', 1, 'johndoe@example.com', 'password1', '555-1111', 'avatar.svg', 1),
(12, 'Robert Turner', 'Male', 1, 'robertturner@example.com', 'password12', '555-2222', 'robert.jpg', 6),
(13, 'Karen Baker', 'Female', 1, 'karenbaker@example.com', 'password13', '555-3333', 'karen.jpg', 6),
(14, 'William Garcia', 'Male', 1, 'williamgarcia@example.com', 'password14', '555-4444', 'william.jpg', 6),
(15, 'Samantha Martinez', 'Female', 1, 'samanthamartinez@example.com', 'password15', '555-5555', 'samantha.jpg', 6),
(16, 'Joseph Hernandez', 'Male', 1, 'josephhernandez@example.com', 'password16', '555-6666', 'joseph.jpg', 6),
(17, 'Nancy Davis', 'Female', 1, 'nancydavis@example.com', 'password17', '555-7777', 'nancy.jpg', 6),
(18, 'Daniel Rodriguez', 'Male', 1, 'danielrodriguez@example.com', 'password18', '555-8888', 'daniel.jpg', 6),
(19, 'Linda Martinez', 'Female', 1, 'lindamartinez@example.com', 'password19', '555-9999', 'linda.jpg', 6),
(20, 'Jennifer Adams', 'Male', 1, 'jenniferadams@example.com', 'password11', '555-1111', 'jennifer.jpg', 6),
(21, 'Christopher Smith', 'Female', 1, 'christophersmith@example.com', 'password20', '555-1010', 'christopher.jpg', 6);

-- --------------------------------------------------------

--
-- Table structure for table `faqs`
--

CREATE TABLE `faqs` (
  `id` int(11) NOT NULL,
  `question` text NOT NULL,
  `answer` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `faqs`
--

INSERT INTO `faqs` (`id`, `question`, `answer`) VALUES
(1, 'I’m unable to log in to my account', 'Such common daily problems can be easily fixed in most scenarios. Make sure the user is not typing the password with the Caps Lock button on. In case the password has expired, share a password reset link to the customer that will allow them to generate a new password by themselves.\r\nSometimes an account can get suspended due to inactivity. Convey the reason to the customer so that they can avoid account suspension in the future.'),
(2, 'My system is running too slowly.', 'You need to first identify the reason that is causing the system to run slowly compared to other systems. Some reasons can be:\r\n\r\nIf an employee is using many heavy applications simultaneously, the system is bound to run slowly. Ask the employee to run only necessary applications and close the ones, not in use.\r\nThe computer might be carrying out updates in the background without an employee’s knowledge. Ask the employee to pause the updates and resume after working hours.\r\nViruses and malware can also impact a computer’s speed. This common IT problem can be fixed by either installing a new antivirus software or disconnecting the system from the office network.'),
(3, 'I can’t help but stare at the blue screen of death.', 'Ask the employee to read and tell you what exactly is written on the blue screen. Every IT support team is well aware that every error message signifies something and can lead to the root cause of the issue.\r\nIn most situations, the blue screen appears when a hardware or a driver is not working properly. The problem usually occurs when someone installs a new hardware or updates the drivers. The IT support staff should inquire from the individual about what exactly he did before the error occurred.'),
(4, 'My PC is shutting down after irregular intervals for no reason', 'IT help desk agents need to identify the root cause of this issue. Some popular reasons can be:\r\n• After long usage of any computer, a great amount of dust can pile up on the cooling fan and impact its proper functioning. If the cooling fan does not work properly, the system can overheat and automatically shut down to prevent major damage.\r\n• This problem is also common with laptop users when the battery is weak or old. Therefore, the laptop battery must be charged properly and replaced when the need arises.'),
(5, 'The printer never seems to work.', 'IT issues like this require a desktop support professional to visit the printer and check the problem. If every employee starts opening the printer parts by themselves, it can lead to more serious problems. To begin with, check if the printer is properly plugged in, has paper in the right tray, and sufficient ink.'),
(6, 'The internet is really… really slow today.', 'This issue can be fixed with common troubleshooting measures. If the problem is occurring with only a single user, you can ask the employee to refresh his networks and ensure no software updates are being downloaded in the background.\r\nOn the other hand, if more employees are complaining of a slow internet connection, these can be the possible help desk solutions:\r\n• Create more WiFi hotspots in the office so that internet signals are strong even at corner desks.\r\n• Make it a strict company policy to prevent users from downloading heavy files such as movies or games during office hours. You can even block torrent sites.'),
(7, 'THEY’VE DELETED FILES THEY SHOULDN’T HAVE', 'First check to determine if the file is in the recycling bin or not. If the recycling bin has been emptied, you may need to reinstate the file for the user from the server backup'),
(8, 'COMPUTER IS TOO SLOW', 'Assess the user’s CPU usage to determine if they have too many apps running at once – especially if these use up a lot of memory. Remove any temporary files from the Windows folder with the user’s permission and delete any large unused programs and files taking up space on their hard disk drive. Also, check that the user does not have viruses or malware on their machine'),
(9, 'USER HAS LOST ACCESS TO THE SHARED DRIVE', 'Ping the server to see that the user is able to connect with it. Then you will have to help them to remap their network drives so they can access them once more.'),
(10, 'COMPUTER HAS A VIRUS', 'Immediately have the user remove their machine from the network. The next steps will depend on the actual virus that is on the machine and what is needed to remove it.'),
(11, 'KEYBOARD OR MOUSE AREN’T WORKING', 'Determine if the Bluetooth connection between the mouse, keyboard and computer is established and working. The next step is to get the user to change batteries.'),
(12, 'COMPUTER WON’T START.', 'Step the user through various potential fixes such as ensuring the power cord is plugged in and switched on. The next step is to get them to perform a reset. If it still isn’t working, you may need to deploy tech to investigate further.'),
(13, 'My Password is Lost, need to Reset.', 'In some organizational setups, G-suite is configured in such a way that only an admin can reset the password of a user. In such a case, people head straight to the helpdesk and create a ticket whenever they forget their password.'),
(14, 'Lost access to the shared drive', 'A problem that can be resolved through instructions and with minimum intervention. In Motadata, admin can create a separate category for such tickets and an SLA. The service agreement will make sure not much time is spent on such tickets. The resolution can be done first through an automated email and then, if still not resolved, through remote desktop.'),
(15, 'I can’t open a program', 'Make sure the program is installed correctly. Try restarting your computer. Check the program’s compatibility with your operating system. Contact the program’s developer for assistance.'),
(16, 'I can’t play a video', 'Make sure the video file is not corrupted. Try restarting your computer. Check your video player settings. Contact the video’s creator for assistance.'),
(17, 'I can’t install a program.', 'Make sure you have the correct permissions to install the program. Try restarting your computer. Download the program from a trusted source. Contact the program’s developer for assistance.'),
(18, 'My email is not working', 'Make sure your email program is up to date. Check your email settings and make sure they are correct. Restart your computer. Contact your email service provider for assistance'),
(19, 'I can’t connect to my wireless network', 'Make sure your computer is turned on and connected to the power outlet. Check your wireless network settings and make sure they are correct. Restart your modem and router. Try connecting to your wireless network from a different device.'),
(20, 'I can’t access a website', 'Check your internet connection. Make sure the website is not down. Try accessing the website from a different computer.');

-- --------------------------------------------------------

--
-- Structure for view `complaint_by_month`
--
DROP TABLE IF EXISTS `complaint_by_month`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `complaint_by_month`  AS SELECT `comp`.`id` AS `id`, `comp`.`priority` AS `priority`, `comp`.`created_date` AS `created_date`, `comp`.`closed_date` AS `closed_date`, `emp`.`full_name` AS `employee`, `cat`.`id` AS `cat_id`, `cat`.`name` AS `category`, `dep`.`id` AS `dep_id`, `dep`.`name` AS `department`, `tech`.`full_name` AS `technician`, date_format(`comp`.`created_date`,'%Y-%m') AS `formatted_month`, yearweek(`comp`.`created_date`,3) AS `week_number`, cast(`comp`.`created_date` as date) AS `date` FROM ((((`compliants` `comp` join `employees` `emp` on(`emp`.`id` = `comp`.`emp_id`)) join `departments` `dep` on(`dep`.`id` = `emp`.`dep_id`)) join `employees` `tech` on(`tech`.`id` = `comp`.`tech_id`)) join `categories` `cat` on(`cat`.`id` = `comp`.`cat_id`)) WHERE `comp`.`status` = 'closed' ORDER BY `comp`.`id` ASC ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `blogs`
--
ALTER TABLE `blogs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `compliants`
--
ALTER TABLE `compliants`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cat_id` (`cat_id`),
  ADD KEY `emp_id` (`emp_id`),
  ADD KEY `tech_id` (`tech_id`);

--
-- Indexes for table `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `department_rel` (`dep_id`);

--
-- Indexes for table `faqs`
--
ALTER TABLE `faqs`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `blogs`
--
ALTER TABLE `blogs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `compliants`
--
ALTER TABLE `compliants`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `departments`
--
ALTER TABLE `departments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `faqs`
--
ALTER TABLE `faqs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `compliants`
--
ALTER TABLE `compliants`
  ADD CONSTRAINT `compliants_ibfk_1` FOREIGN KEY (`cat_id`) REFERENCES `categories` (`id`),
  ADD CONSTRAINT `compliants_ibfk_2` FOREIGN KEY (`emp_id`) REFERENCES `employees` (`id`),
  ADD CONSTRAINT `compliants_ibfk_3` FOREIGN KEY (`tech_id`) REFERENCES `employees` (`id`);

--
-- Constraints for table `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `department_rel` FOREIGN KEY (`dep_id`) REFERENCES `departments` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
