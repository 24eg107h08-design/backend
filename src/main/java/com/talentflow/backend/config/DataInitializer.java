package com.talentflow.backend.config;

import com.talentflow.backend.model.Application;
import com.talentflow.backend.model.Interview;
import com.talentflow.backend.model.Job;
import com.talentflow.backend.model.Role;
import com.talentflow.backend.model.User;
import com.talentflow.backend.repository.ApplicationRepository;
import com.talentflow.backend.repository.InterviewRepository;
import com.talentflow.backend.repository.JobRepository;
import com.talentflow.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final InterviewRepository interviewRepository;

    public DataInitializer(UserRepository userRepository,
                           JobRepository jobRepository,
                           ApplicationRepository applicationRepository,
                           InterviewRepository interviewRepository) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
        this.interviewRepository = interviewRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            log.info("Initializing SQL Database with starter recruitment records...");

            // 1. Seed Users
            User admin = new User("Sarah Connor", "sarah.admin@enterprise.io", Role.ADMIN, "Platform Engineering", "Super Administrator");
            admin.setAvatar("https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=150");
            admin.setJoinedDate("Jan 15, 2024");
            userRepository.save(admin);

            User hr1 = new User("Michael Scott", "m.scott@enterprise.io", Role.HR, "Talent Acquisition", "Senior HR Manager");
            hr1.setAvatar("https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=150");
            hr1.setJoinedDate("Feb 01, 2024");
            userRepository.save(hr1);

            User candidate = new User("Alex Rivera", "alex.rivera@gmail.com", Role.CANDIDATE, "Engineering", "Frontend Engineer");
            candidate.setAvatar("https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=150");
            candidate.setJoinedDate("Mar 10, 2024");
            candidate.setPhone("+1 (555) 234-5678");
            candidate.setLocation("Austin, TX (Open to Remote)");
            candidate.setBio("Passionate frontend architect specialized in React 19, TypeScript, and high-performance user interfaces.");
            userRepository.save(candidate);

            User hr2 = new User("Jessica Pearson", "jessica.hr@enterprise.io", Role.HR, "Human Resources", "Head of Talent");
            hr2.setAvatar("https://images.unsplash.com/photo-1573496359142-b8d87734a5a2?w=150");
            hr2.setJoinedDate("Dec 05, 2023");
            userRepository.save(hr2);

            User candidate2 = new User("David Chen", "david.c@enterprise.io", Role.CANDIDATE, "Design", "Product Designer");
            candidate2.setAvatar("https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=150");
            candidate2.setJoinedDate("Nov 12, 2023");
            candidate2.setStatus("Inactive");
            userRepository.save(candidate2);

            // 2. Seed Jobs
            Job job1 = new Job(
                    "Senior Frontend Engineer",
                    "Engineering",
                    "Remote (US/EU)",
                    "Full-time",
                    "$130k - $160k",
                    "We are seeking an experienced React/TypeScript engineer to scale our core web application architecture.",
                    Arrays.asList("5+ years React & TypeScript", "State management (Zustand/Redux)", "Tailwind CSS expertise", "REST & GraphQL APIs")
            );
            job1.setApplicantsCount(42);
            job1.setPostedDate("2 days ago");
            Job savedJob1 = jobRepository.save(job1);

            Job job2 = new Job(
                    "Full Stack Developer",
                    "Product",
                    "San Francisco, CA",
                    "Full-time",
                    "$120k - $150k",
                    "Join our cross-functional team building next-generation productivity tools with React, Node.js, and Postgres.",
                    Arrays.asList("3+ years Node.js and React", "PostgreSQL and Prisma", "Docker & CI/CD workflows", "Agile sprint delivery")
            );
            job2.setApplicantsCount(29);
            job2.setPostedDate("5 days ago");
            Job savedJob2 = jobRepository.save(job2);

            Job job3 = new Job(
                    "Product Designer (UI/UX)",
                    "Design",
                    "Remote",
                    "Full-time",
                    "$110k - $140k",
                    "Lead design systems, user journey wireframes, and high-fidelity prototypes for enterprise portals.",
                    Arrays.asList("Figma mastery", "Design systems documentation", "User research & usability testing", "Collaborative mindset")
            );
            job3.setApplicantsCount(37);
            job3.setPostedDate("1 week ago");
            Job savedJob3 = jobRepository.save(job3);

            Job job4 = new Job(
                    "DevOps & Cloud Architect",
                    "Infrastructure",
                    "New York, NY",
                    "Contract",
                    "$140k - $175k",
                    "Modernize AWS cloud infrastructure, Kubernetes clusters, and automated zero-downtime deployment pipelines.",
                    Arrays.asList("AWS certified Solutions Architect", "Kubernetes and Terraform", "Terraform & Helm", "Prometheus & Grafana monitoring")
            );
            job4.setStatus("Paused");
            job4.setApplicantsCount(18);
            job4.setPostedDate("2 weeks ago");
            jobRepository.save(job4);

            // 3. Seed Applications
            Application app1 = new Application(
                    savedJob1.getId(),
                    "Senior Frontend Engineer",
                    "NextGen Solutions",
                    "Alex Rivera",
                    "alex.rivera@gmail.com",
                    "Interview",
                    3,
                    "94%",
                    "Strong portfolio with React 19, Tailwind CSS, and Java REST APIs."
            );
            app1.setAppliedDate("Nov 02, 2026");
            applicationRepository.save(app1);

            Application app2 = new Application(
                    savedJob2.getId(),
                    "Full Stack Developer",
                    "FinTech Cloud",
                    "Alex Rivera",
                    "alex.rivera@gmail.com",
                    "Under Review",
                    2,
                    "88%",
                    "Resume passed initial ATS screening."
            );
            app2.setAppliedDate("Oct 28, 2026");
            applicationRepository.save(app2);

            Application app3 = new Application(
                    savedJob3.getId(),
                    "Product Designer (UI/UX)",
                    "Starlight Media",
                    "Elena Rostova",
                    "elena.r@designhub.net",
                    "Offer",
                    4,
                    "96%",
                    "Offer letter extended. Waiting for confirmation."
            );
            app3.setAppliedDate("Nov 05, 2026");
            applicationRepository.save(app3);

            Application app4 = new Application(
                    savedJob1.getId(),
                    "Senior Frontend Engineer",
                    "NextGen Solutions",
                    "David Chen",
                    "david.c@enterprise.io",
                    "Rejected",
                    1,
                    "65%",
                    "Insufficient frontend architecture experience."
            );
            app4.setAppliedDate("Oct 20, 2026");
            applicationRepository.save(app4);

            // 4. Seed Interviews
            interviewRepository.save(new Interview(
                    "Alex Rivera",
                    "Senior Frontend Engineer",
                    "Michael Scott (HR) & Lead Architect",
                    "Nov 10, 2026",
                    "2:30 PM EST",
                    "Technical Deep-Dive",
                    "https://meet.google.com/abc-defg-hij",
                    "Scheduled"
            ));

            interviewRepository.save(new Interview(
                    "Elena Rostova",
                    "Product Designer (UI/UX)",
                    "Jessica Pearson (HR)",
                    "Nov 12, 2026",
                    "11:00 AM EST",
                    "Culture & Offer Discussion",
                    "https://meet.google.com/xyz-uvwx-rst",
                    "Confirmed"
            ));

            interviewRepository.save(new Interview(
                    "Marcus Vance",
                    "Full Stack Developer",
                    "Michael Scott (HR)",
                    "Nov 14, 2026",
                    "4:00 PM EST",
                    "Initial Screening",
                    "https://meet.google.com/mno-pqrs-tuv",
                    "Scheduled"
            ));

            log.info("Database seeding completed successfully. Users: {}, Jobs: {}, Applications: {}, Interviews: {}",
                    userRepository.count(), jobRepository.count(), applicationRepository.count(), interviewRepository.count());
        }
    }
}
